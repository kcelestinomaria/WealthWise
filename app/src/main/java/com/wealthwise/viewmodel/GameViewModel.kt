package com.wealthwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wealthwise.data.database.entities.TransactionEntity
import com.wealthwise.data.model.DecisionOption
import com.wealthwise.data.model.LeaderboardEntry
import com.wealthwise.data.model.Player
import com.wealthwise.data.model.Role
import com.wealthwise.data.model.Transaction
import com.wealthwise.data.model.TransactionType
import com.wealthwise.data.model.TurnEvent
import com.wealthwise.data.repository.PlayerRepository
import com.wealthwise.data.repository.LeaderboardRepository
import com.wealthwise.engine.GameEngine
import com.wealthwise.engine.getGeneralEvents
import com.wealthwise.engine.getEventsForRole
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.sql.Date


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

                // Convert Transaction to TransactionEntity before inserting
                val transactionEntity = TransactionEntity(
                    sessionId = "", // You'll need to provide this
                    playerId = player.id.toString(),
                    type = gameResult.transaction.type,
                    amount = gameResult.transaction.amount.toInt(),
                    description = gameResult.transaction.description,
                    day = gameResult.transaction.day,
                    balanceBefore = 0, // You'll need to provide these
                    balanceAfter = 0,
                    createdAt = Date(gameResult.transaction.timestamp)
                )

                // Insert transaction
                playerRepository.insertTransaction(transactionEntity)
                
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

                // Record income transaction - convert to TransactionEntity
                val transactionEntity = TransactionEntity(
                    sessionId = "", // You should provide the actual session ID here
                    playerId = player.id.toString(),
                    type = TransactionType.INCOME,
                    amount = income.toInt(),
                    description = "Daily income (${player.role.displayName})",
                    day = player.currentDay,
                    balanceBefore = player.cash.toInt(),
                    balanceAfter = (player.cash + income).toInt(),
                    createdAt = Date(System.currentTimeMillis())
                )
                playerRepository.insertTransaction(transactionEntity)
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
            .map { entityList ->
                entityList.map { entity ->
                    Transaction(
                        id = entity.transactionId.hashCode().toLong(),
                        playerId = entity.playerId.toLong(),
                        day = entity.day,
                        type = entity.type,
                        amount = entity.amount.toDouble(),
                        description = entity.description,
                        timestamp = entity.createdAt.time
                    )
                }
            }
    }
} 