package com.wealthwise.data.repository

import com.wealthwise.data.dao.PlayerDao
import com.wealthwise.data.dao.TransactionDao
import com.wealthwise.data.database.entities.TransactionEntity
import com.wealthwise.data.model.Player
import kotlinx.coroutines.flow.Flow



class PlayerRepository  constructor(
    private val playerDao: PlayerDao,
    private val transactionDao: TransactionDao
) {
    
    fun getAllPlayers(): Flow<List<Player>> = playerDao.getAllPlayers()
    
    fun getPlayerById(playerId: Long): Flow<Player?> = playerDao.getPlayerById(playerId)
    
    fun getCurrentPlayer(): Flow<Player?> = playerDao.getCurrentPlayer()
    
    fun getCompletedPlayers(): Flow<List<Player>> = playerDao.getCompletedPlayers()
    
    suspend fun insertPlayer(player: Player): Long = playerDao.insertPlayer(player)
    
    suspend fun updatePlayer(player: Player) = playerDao.updatePlayer(player)
    
    suspend fun deletePlayer(player: Player) = playerDao.deletePlayer(player)
    
    suspend fun deletePlayerById(playerId: Long) = playerDao.deletePlayerById(playerId)
    
    // Transaction operations
    fun getTransactionsByPlayer(playerId: Long): Flow<List<TransactionEntity>> =
        transactionDao.getTransactionsByPlayer(playerId.toString())
    
    suspend fun insertTransaction(transaction: TransactionEntity): Long =
        transactionDao.insertTransaction(transaction)
    
    suspend fun deleteTransactionsByPlayer(playerId: Long) = 
        transactionDao.deleteTransactionsByPlayer(playerId.toString())
} 