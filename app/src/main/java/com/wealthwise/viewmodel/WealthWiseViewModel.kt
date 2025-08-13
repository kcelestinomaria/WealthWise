package com.wealthwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wealthwise.data.model.*
import com.wealthwise.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import com.wealthwise.data.model.GameState
import com.wealthwise.data.model.Goal
import com.wealthwise.data.model.Career
import com.wealthwise.data.model.GoalCategory
import com.wealthwise.data.model.TransactionType
import com.wealthwise.data.model.CareersData
import kotlin.text.Typography.dagger

/**
 * UI State data class - manages all UI-related state including dialog visibility
 * and user messages. Separate from game state to handle UI concerns independently.
 */
data class WealthWiseUiState(
    val gameState: GameState = GameState(),
    val isOnboardingCompleted: Boolean = false,
    // Dialog visibility flags
    val showPayBillsDialog: Boolean = false,
    val showInvestmentDialog: Boolean = false,
    val showLoanDialog: Boolean = false,
    val showAddGoalDialog: Boolean = false,
    val showEditGoalDialog: Boolean = false,
    val selectedGoal: Goal? = null,
    // User feedback messages
    val errorMessage: String? = null,
    val successMessage: String? = null
)

/**
 * WealthWiseViewModel - Core business logic and state management
 *
 * Responsibilities:
 * - Manage game state (balance, day, goals, career)
 * - Handle financial actions (pay bills, invest, take loans)
 * - Control dialog visibility and user interactions
 * - Manage goal creation, editing, and progress tracking
 * - Format currency and provide utility functions
 * - Persist game data to database and manage user sessions
 *
 * State flows:
 * - uiState: UI-related state (dialogs, messages)
 * - gameState: Game progress and financial data
 */
@HiltViewModel
class WealthWiseViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    // UI state management
    private val _uiState = MutableStateFlow(WealthWiseUiState())
    val uiState: StateFlow<WealthWiseUiState> = _uiState.asStateFlow()

    // Game state management
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    // Database session management
    private var currentUserId: String? = null
    private var currentSessionId: String? = null

    /**
     * Initialize user session - Load existing game or create new one
     */
    suspend fun initializeUserSession(userId: String) {
        currentUserId = userId

        // Get or create current game session
        val existingSession = databaseRepository.getCurrentSession(userId)
        currentSessionId = existingSession?.sessionId ?: run {
            // Create new session with default career
            val defaultCareer = CareersData.careers.first()
            databaseRepository.createGameSession(
                userId = userId,
                career = defaultCareer.title,
                monthlyIncome = defaultCareer.salary,
                monthlyExpenses = (defaultCareer.salary * 0.7).toInt(),
                careerId = defaultCareer.id
            )
        }

        // Initialize achievements if new user
        if (existingSession == null) {
            databaseRepository.initializeAchievements(userId)
        }

        // Load game state from database
        loadGameStateFromDatabase()
    }

    /**
     * Load game state from database and update UI
     */
    private suspend fun loadGameStateFromDatabase() {
        currentUserId?.let { userId ->
            currentSessionId?.let { sessionId ->
                val session = databaseRepository.getCurrentSession(userId)
                session?.let { sessionEntity ->
                    val updatedGameState = GameState(
                        currentDay = sessionEntity.currentDay,
                        balance = sessionEntity.balance,
                        savings = sessionEntity.savings,
                        debt = sessionEntity.debt,
                        monthlyIncome = sessionEntity.monthlyIncome,
                        career = sessionEntity.career,
                        monthlyExpenses = sessionEntity.monthlyExpenses,
                        isOnboardingCompleted = true,
                        selectedCareerId = sessionEntity.careerId.toString(),
                        goals = sessionEntity.goals
                    )
                    _gameState.value = updatedGameState
                    _uiState.value = _uiState.value.copy(
                        gameState = updatedGameState,
                        isOnboardingCompleted = true
                    )
                }
            }
        }
    }

    /**
     * Save current game state to database
     */
    private suspend fun saveGameStateToDatabase(gameState: GameState) {
        currentUserId?.let { userId ->
            val session = databaseRepository.getCurrentSession(userId)
            session?.let { sessionEntity ->
                val updatedSession = sessionEntity.copy(
                    currentDay = gameState.currentDay,
                    balance = gameState.balance,
                    savings = gameState.savings,
                    debt = gameState.debt,
                    monthlyIncome = gameState.monthlyIncome,
                    career = gameState.career,
                    monthlyExpenses = gameState.monthlyExpenses,
                    goals = gameState.goals,
                    lastPlayedAt = Date()
                )
                databaseRepository.updateGameSession(updatedSession)
            }
        }
    }

    /**
     * Initialize game with selected career - sets starting income, balance, and expenses
     * Starting balance is 30% of monthly salary to simulate some initial savings
     */
    fun selectCareer(career: Career) {
        viewModelScope.launch {
            val startingBalance = (career.salary * 0.3).toInt()  // Start with 30% of monthly income
            val updatedGameState = _gameState.value.copy(
                career = career.title,
                monthlyIncome = career.salary,
                balance = startingBalance,
                selectedCareerId = career.id,
                isOnboardingCompleted = true
            )

            // Save to database
            saveGameStateToDatabase(updatedGameState)

            _gameState.value = updatedGameState
            _uiState.value = _uiState.value.copy(
                gameState = updatedGameState,
                isOnboardingCompleted = true
            )
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            val updatedGameState = _gameState.value.copy(isOnboardingCompleted = true)

            // Save to database
            saveGameStateToDatabase(updatedGameState)

            _gameState.value = updatedGameState
            _uiState.value = _uiState.value.copy(
                gameState = updatedGameState,
                isOnboardingCompleted = true
            )
        }
    }

    fun showPayBillsDialog() {
        _uiState.value = _uiState.value.copy(showPayBillsDialog = true)
    }

    fun hidePayBillsDialog() {
        _uiState.value = _uiState.value.copy(showPayBillsDialog = false)
    }

    fun showInvestmentDialog() {
        _uiState.value = _uiState.value.copy(showInvestmentDialog = true)
    }

    fun hideInvestmentDialog() {
        _uiState.value = _uiState.value.copy(showInvestmentDialog = false)
    }

    fun showLoanDialog() {
        _uiState.value = _uiState.value.copy(showLoanDialog = true)
    }

    fun hideLoanDialog() {
        _uiState.value = _uiState.value.copy(showLoanDialog = false)
    }

    /**
     * Pay monthly bills - core game mechanic for advancing time
     * Now with database persistence and transaction recording
     *
     * Game flow:
     * 1. Check if player has enough money for monthly expenses
     * 2. If yes: deduct expenses, advance to next day, save to database
     * 3. If no: show error, stay on current day
     *
     * This teaches budgeting and the importance of covering essential expenses
     */
    fun payBills() {
        viewModelScope.launch {
            val currentState = _gameState.value

            if (currentState.balance >= currentState.monthlyExpenses) {
                val balanceBefore = currentState.balance
                val balanceAfter = balanceBefore - currentState.monthlyExpenses

                // Record transaction
                currentSessionId?.let { sessionId ->
                    databaseRepository.recordTransaction(
                        sessionId = sessionId,
                        type = TransactionType.BILL_PAYMENT,
                        amount = currentState.monthlyExpenses,
                        description = "Monthly bills payment",
                        balanceBefore = balanceBefore,
                        balanceAfter = balanceAfter,
                        day = currentState.currentDay
                    )
                }

                val updatedState = currentState.copy(
                    balance = balanceAfter,
                    currentDay = currentState.currentDay + 1  // Advance game time
                )

                // Save to database
                saveGameStateToDatabase(updatedState)

                _gameState.value = updatedState
                _uiState.value = _uiState.value.copy(
                    gameState = updatedState,
                    showPayBillsDialog = false,
                    successMessage = "Bills paid successfully! Day advanced to ${updatedState.currentDay}."
                )
            } else {
                // Not enough money - stay on same day, show error
                _uiState.value = _uiState.value.copy(
                    showPayBillsDialog = false,
                    errorMessage = "Insufficient funds to pay bills!"
                )
            }
        }
    }

    /**
     * Invest money - move from liquid balance to savings/investments
     * Teaches the concept of growing wealth through investments (8% return mentioned in UI)
     */
    fun invest(amount: Int) {
        viewModelScope.launch {
            val currentState = _gameState.value

            if (amount > 0 && amount <= currentState.balance) {
                val balanceBefore = currentState.balance
                val balanceAfter = balanceBefore - amount

                // Record transaction
                currentSessionId?.let { sessionId ->
                    databaseRepository.recordTransaction(
                        sessionId = sessionId,
                        type = TransactionType.INVESTMENT,
                        amount = amount,
                        description = "Investment in savings",
                        balanceBefore = balanceBefore,
                        balanceAfter = balanceAfter,
                        day = currentState.currentDay
                    )
                }

                val updatedState = currentState.copy(
                    balance = balanceAfter,  // Remove from liquid cash
                    savings = currentState.savings + amount   // Add to invested savings
                )

                // Save to database
                saveGameStateToDatabase(updatedState)

                _gameState.value = updatedState
                _uiState.value = _uiState.value.copy(
                    gameState = updatedState,
                    showInvestmentDialog = false,
                    successMessage = "Investment of Ksh $amount made successfully!"
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    showInvestmentDialog = false,
                    errorMessage = "Invalid investment amount or insufficient funds!"
                )
            }
        }
    }

    /**
     * Take a loan - instantly add money but also add debt
     * Teaches credit concepts and the responsibility of debt repayment (12% interest mentioned in UI)
     */
    fun takeLoan(amount: Int) {
        viewModelScope.launch {
            val currentState = _gameState.value

            if (amount > 0) {
                val balanceBefore = currentState.balance
                val balanceAfter = balanceBefore + amount

                // Record transaction
                currentSessionId?.let { sessionId ->
                    databaseRepository.recordTransaction(
                        sessionId = sessionId,
                        type = TransactionType.LOAN,
                        amount = amount,
                        description = "Loan taken",
                        balanceBefore = balanceBefore,
                        balanceAfter = balanceAfter,
                        day = currentState.currentDay
                    )
                }

                val updatedState = currentState.copy(
                    balance = balanceAfter,  // Add borrowed money to balance
                    debt = currentState.debt + amount         // Track debt for net worth calculation
                )

                // Save to database
                saveGameStateToDatabase(updatedState)

                _gameState.value = updatedState
                _uiState.value = _uiState.value.copy(
                    gameState = updatedState,
                    showLoanDialog = false,
                    successMessage = "Loan of Ksh $amount approved and deposited!"
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    showLoanDialog = false,
                    errorMessage = "Invalid loan amount!"
                )
            }
        }
    }

    fun advanceDay() {
        viewModelScope.launch {
            val currentState = _gameState.value
            val updatedState = currentState.copy(currentDay = currentState.currentDay + 1)

            // Save to database
            saveGameStateToDatabase(updatedState)

            _gameState.value = updatedState
            _uiState.value = _uiState.value.copy(gameState = updatedState)
        }
    }

    fun getNetWorth(): Int {
        return _gameState.value.getNetWorth()
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            successMessage = null
        )
    }

    fun resetGame() {
        viewModelScope.launch {
            val newGameState = GameState()

            // Save reset state to database
            saveGameStateToDatabase(newGameState)

            _gameState.value = newGameState
            _uiState.value = WealthWiseUiState()
        }
    }

    // Monthly income simulation (could be called periodically)
    fun addMonthlyIncome() {
        viewModelScope.launch {
            val currentState = _gameState.value
            val balanceBefore = currentState.balance
            val balanceAfter = balanceBefore + currentState.monthlyIncome

            // Record transaction
            currentSessionId?.let { sessionId ->
                databaseRepository.recordTransaction(
                    sessionId = sessionId,
                    type = TransactionType.INCOME,
                    amount = currentState.monthlyIncome,
                    description = "Monthly salary",
                    balanceBefore = balanceBefore,
                    balanceAfter = balanceAfter,
                    day = currentState.currentDay
                )
            }

            val updatedState = currentState.copy(
                balance = balanceAfter
            )

            // Save to database
            saveGameStateToDatabase(updatedState)

            _gameState.value = updatedState
            _uiState.value = _uiState.value.copy(
                gameState = updatedState,
                successMessage = "Monthly salary of Ksh ${currentState.monthlyIncome} received!"
            )
        }
    }

    // =============================================================================
    // GOAL MANAGEMENT METHODS - Handle financial goals creation and progress tracking
    // =============================================================================

    // Dialog visibility controls
    fun showAddGoalDialog() {
        _uiState.value = _uiState.value.copy(showAddGoalDialog = true)
    }

    fun hideAddGoalDialog() {
        _uiState.value = _uiState.value.copy(showAddGoalDialog = false)
    }

    fun showEditGoalDialog(goal: Goal) {
        _uiState.value = _uiState.value.copy(
            showEditGoalDialog = true,
            selectedGoal = goal
        )
    }

    fun hideEditGoalDialog() {
        _uiState.value = _uiState.value.copy(
            showEditGoalDialog = false,
            selectedGoal = null
        )
    }

    /**
     * Create a new financial goal - teaches goal setting and planning
     * Goals help players learn to save for specific targets (emergency fund, purchases, etc.)
     */
    fun addGoal(title: String, description: String, targetAmount: Int, category: GoalCategory, icon: String) {
        viewModelScope.launch {
            val currentState = _gameState.value
            val newGoal = Goal(
                id = java.util.UUID.randomUUID().toString(),
                title = title,
                description = description,
                targetAmount = targetAmount,
                category = category,
                icon = icon
            )

            // Add goal to game state
            val updatedState = currentState.copy(
                goals = currentState.goals + newGoal
            )

            // Save to database
            saveGameStateToDatabase(updatedState)

            _gameState.value = updatedState
            _uiState.value = _uiState.value.copy(
                gameState = updatedState,
                showAddGoalDialog = false,
                successMessage = "Goal '$title' added successfully!"
            )
        }
    }

    /**
     * Contribute money to a goal - teaches saving discipline and progress tracking
     *
     * Process:
     * 1. Take money from player's balance
     * 2. Add to goal's current amount (up to target)
     * 3. Mark goal as completed if target reached
     * 4. Update UI with progress feedback
     */
    fun updateGoal(goalId: String, currentAmount: Int) {
        viewModelScope.launch {
            val currentState = _gameState.value

            // Update the specific goal with contribution
            val updatedGoals = currentState.goals.map { goal ->
                if (goal.id == goalId) {
                    val newCurrentAmount = (goal.currentAmount + currentAmount).coerceAtMost(goal.targetAmount)
                    goal.copy(
                        currentAmount = newCurrentAmount,
                        isCompleted = newCurrentAmount >= goal.targetAmount  // Auto-complete when target reached
                    )
                } else goal
            }

            // Validate player has enough money and amount is valid
            if (currentAmount > 0 && currentAmount <= currentState.balance) {
                val balanceBefore = currentState.balance
                val balanceAfter = balanceBefore - currentAmount

                // Record transaction
                currentSessionId?.let { sessionId ->
                    val goalTitle = currentState.goals.find { it.id == goalId }?.title ?: "Goal"
                    databaseRepository.recordTransaction(
                        sessionId = sessionId,
                        type = TransactionType.GOAL_CONTRIBUTION,
                        amount = currentAmount,
                        description = "Contribution to $goalTitle",
                        balanceBefore = balanceBefore,
                        balanceAfter = balanceAfter,
                        day = currentState.currentDay
                    )
                }

                val updatedState = currentState.copy(
                    goals = updatedGoals,
                    balance = balanceAfter  // Deduct contribution from balance
                )

                // Save to database
                saveGameStateToDatabase(updatedState)

                _gameState.value = updatedState
                _uiState.value = _uiState.value.copy(
                    gameState = updatedState,
                    showEditGoalDialog = false,
                    selectedGoal = null,
                    successMessage = "Goal updated! Ksh $currentAmount contributed."
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    showEditGoalDialog = false,
                    selectedGoal = null,
                    errorMessage = "Invalid amount or insufficient funds!"
                )
            }
        }
    }

    fun removeGoal(goalId: String) {
        viewModelScope.launch {
            val currentState = _gameState.value
            val updatedGoals = currentState.goals.filter { it.id != goalId }

            val updatedState = currentState.copy(goals = updatedGoals)

            // Save to database
            saveGameStateToDatabase(updatedState)

            _gameState.value = updatedState
            _uiState.value = _uiState.value.copy(
                gameState = updatedState,
                showEditGoalDialog = false,
                selectedGoal = null,
                successMessage = "Goal removed successfully!"
            )
        }
    }

    // Utility function to format currency
    fun formatCurrency(amount: Int): String {
        return "Ksh ${java.text.NumberFormat.getNumberInstance(java.util.Locale("en", "KE")).format(amount)}"
    }
}