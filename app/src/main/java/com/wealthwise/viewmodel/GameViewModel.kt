package com.wealthwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wealthwise.data.model.*
import com.wealthwise.data.repository.PlayerRepository
import com.wealthwise.data.repository.LeaderboardRepository
import com.wealthwise.engine.GameEngine
import com.wealthwise.engine.getGeneralEvents
import com.wealthwise.engine.getEventsForRole
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


data class GameUiState(
    val currentPlayer: Player? = null,
    val currentEvent: TurnEvent? = null,
    val gameCompleted: Boolean = false,
    val dailyIncome: Double = 0.0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val lastDecisionResult: String? = null
)

class GameViewModel  constructor(
    private val playerRepository: PlayerRepository,
    private val leaderboardRepository: LeaderboardRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    
    private val _currentPlayer = MutableStateFlow<Player?>(null)
    val currentPlayer: StateFlow<Player?> = _currentPlayer.asStateFlow()
    
    init {
        loadCurrentPlayer()
    }
    
    private fun loadCurrentPlayer() {
        viewModelScope.launch {
            playerRepository.getCurrentPlayer().collect { player ->
                _currentPlayer.value = player
                _uiState.value = _uiState.value.copy(
                    currentPlayer = player,
                    gameCompleted = player?.gameCompleted ?: false
                )
                
                if (player != null && !player.gameCompleted) {
                    generateDailyEvent(player)
                    generateDailyIncome(player)
                }
            }
        }
    }
    
    fun startNewGame(playerName: String, selectedRole: Role) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                val newPlayer = Player.createNew(playerName, selectedRole)
                playerRepository.insertPlayer(newPlayer)
                
                _uiState.value = _uiState.value.copy(isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to start new game: ${e.message}"
                )
            }
        }
    }
    
    fun makeDecision(option: DecisionOption) {
        val player = _currentPlayer.value ?: return
        
        viewModelScope.launch {
            try {
                val gameResult = GameEngine.processDecision(player, option)
                
                // Update player
                playerRepository.updatePlayer(gameResult.updatedPlayer)
                
                // Insert transaction
                playerRepository.insertTransaction(gameResult.transaction)
                
                // Update UI with result
                _uiState.value = _uiState.value.copy(
                    lastDecisionResult = gameResult.message
                )
                
                // Check if game is completed
                if (gameResult.updatedPlayer.gameCompleted) {
                    completeGame(gameResult.updatedPlayer)
                }
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error processing decision: ${e.message}"
                )
            }
        }
    }
    
    private fun generateDailyEvent(player: Player) {
        val event = GameEngine.generateDailyEvent(player.currentDay, player.role)
        _uiState.value = _uiState.value.copy(currentEvent = event)
    }
    
    private fun generateDailyIncome(player: Player) {
        val income = GameEngine.generateDailyIncome(player.role, player.currentDay)
        _uiState.value = _uiState.value.copy(dailyIncome = income)
        
        if (income > 0) {
            viewModelScope.launch {
                val updatedPlayer = player.copy(cash = player.cash + income)
                playerRepository.updatePlayer(updatedPlayer)
                
                // Record income transaction
                val transaction = Transaction(
                    playerId = player.id,
                    day = player.currentDay,
                    type = TransactionType.INCOME,
                    amount = income,
                    description = "Daily income (${player.role.displayName})"
                )
                playerRepository.insertTransaction(transaction)
            }
        }
    }
    
    private suspend fun completeGame(player: Player) {
        try {
            // Create leaderboard entry
            val leaderboardEntry = LeaderboardEntry.fromPlayer(player)
            leaderboardRepository.insertEntry(leaderboardEntry)
            
            // Try to sync to Firebase
            leaderboardRepository.syncToFirebase(leaderboardEntry)
            
            _uiState.value = _uiState.value.copy(gameCompleted = true)
            
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Game completed but failed to save score: ${e.message}"
            )
        }
    }
    
    fun resetGame() {
        _currentPlayer.value?.let { player ->
            viewModelScope.launch {
                playerRepository.deletePlayer(player)
                playerRepository.deleteTransactionsByPlayer(player.id)
                _uiState.value = GameUiState()
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
    
    fun clearLastResult() {
        _uiState.value = _uiState.value.copy(lastDecisionResult = null)
    }
    
    fun getTransactionsForPlayer(playerId: Long): Flow<List<Transaction>> {
        return playerRepository.getTransactionsByPlayer(playerId)
    }
} 