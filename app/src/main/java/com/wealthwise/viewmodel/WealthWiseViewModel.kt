package com.wealthwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wealthwise.data.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
 * 
 * State flows:
 * - uiState: UI-related state (dialogs, messages)
 * - gameState: Game progress and financial data
 */
class WealthWiseViewModel : ViewModel() {
    
    // UI state management
    private val _uiState = MutableStateFlow(WealthWiseUiState())
    val uiState: StateFlow<WealthWiseUiState> = _uiState.asStateFlow()
    
    // Game state management  
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()
    
    /**
     * Initialize game with selected career - sets starting income, balance, and expenses
     * Starting balance is 30% of monthly salary to simulate some initial savings
     */
    fun selectCareer(career: Career) {
        val startingBalance = (career.salary * 0.3).toInt()  // Start with 30% of monthly income
        val updatedGameState = _gameState.value.copy(
            career = career.title,
            monthlyIncome = career.salary,
            balance = startingBalance,
            selectedCareerId = career.id,
            isOnboardingCompleted = true
        )
        
        _gameState.value = updatedGameState
        _uiState.value = _uiState.value.copy(
            gameState = updatedGameState,
            isOnboardingCompleted = true
        )
    }
    
    fun completeOnboarding() {
        val updatedGameState = _gameState.value.copy(isOnboardingCompleted = true)
        _gameState.value = updatedGameState
        _uiState.value = _uiState.value.copy(
            gameState = updatedGameState,
            isOnboardingCompleted = true
        )
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
     * 
     * Game flow:
     * 1. Check if player has enough money for monthly expenses
     * 2. If yes: deduct expenses, advance to next day
     * 3. If no: show error, stay on current day
     * 
     * This teaches budgeting and the importance of covering essential expenses
     */
    fun payBills() {
        val currentState = _gameState.value
        
        if (currentState.balance >= currentState.monthlyExpenses) {
            val updatedState = currentState.copy(
                balance = currentState.balance - currentState.monthlyExpenses,
                currentDay = currentState.currentDay + 1  // Advance game time
            )
            
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
    
    /**
     * Invest money - move from liquid balance to savings/investments
     * Teaches the concept of growing wealth through investments (8% return mentioned in UI)
     */
    fun invest(amount: Int) {
        val currentState = _gameState.value
        
        if (amount > 0 && amount <= currentState.balance) {
            val updatedState = currentState.copy(
                balance = currentState.balance - amount,  // Remove from liquid cash
                savings = currentState.savings + amount   // Add to invested savings
            )
            
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
    
    /**
     * Take a loan - instantly add money but also add debt
     * Teaches credit concepts and the responsibility of debt repayment (12% interest mentioned in UI)
     */
    fun takeLoan(amount: Int) {
        val currentState = _gameState.value
        
        if (amount > 0) {
            val updatedState = currentState.copy(
                balance = currentState.balance + amount,  // Add borrowed money to balance
                debt = currentState.debt + amount         // Track debt for net worth calculation
            )
            
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
    
    fun advanceDay() {
        val currentState = _gameState.value
        val updatedState = currentState.copy(currentDay = currentState.currentDay + 1)
        
        _gameState.value = updatedState
        _uiState.value = _uiState.value.copy(gameState = updatedState)
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
        _gameState.value = GameState()
        _uiState.value = WealthWiseUiState()
    }
    
    // Monthly income simulation (could be called periodically)
    fun addMonthlyIncome() {
        val currentState = _gameState.value
        val updatedState = currentState.copy(
            balance = currentState.balance + currentState.monthlyIncome
        )
        
        _gameState.value = updatedState
        _uiState.value = _uiState.value.copy(
            gameState = updatedState,
            successMessage = "Monthly salary of Ksh ${currentState.monthlyIncome} received!"
        )
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
        
        _gameState.value = updatedState
        _uiState.value = _uiState.value.copy(
            gameState = updatedState,
            showAddGoalDialog = false,
            successMessage = "Goal '$title' added successfully!"
        )
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
            val updatedState = currentState.copy(
                goals = updatedGoals,
                balance = currentState.balance - currentAmount  // Deduct contribution from balance
            )
            
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
    
    fun removeGoal(goalId: String) {
        val currentState = _gameState.value
        val updatedGoals = currentState.goals.filter { it.id != goalId }
        
        val updatedState = currentState.copy(goals = updatedGoals)
        _gameState.value = updatedState
        _uiState.value = _uiState.value.copy(
            gameState = updatedState,
            showEditGoalDialog = false,
            selectedGoal = null,
            successMessage = "Goal removed successfully!"
        )
    }

    // Utility function to format currency
    fun formatCurrency(amount: Int): String {
        return "Ksh ${java.text.NumberFormat.getNumberInstance(java.util.Locale("en", "KE")).format(amount)}"
    }
}