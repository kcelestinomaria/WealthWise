package com.wealthwise.data.dao

import androidx.room.*
import com.wealthwise.data.database.entities.TransactionEntity
import com.wealthwise.data.model.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_entities WHERE session_id = :sessionId ORDER BY created_at DESC")
    fun getTransactionsBySessionFlow(sessionId: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_entities WHERE session_id = :sessionId ORDER BY created_at DESC LIMIT :limit")
    suspend fun getRecentTransactions(sessionId: String, limit: Int = 20): List<TransactionEntity>

    @Query("SELECT * FROM transaction_entities WHERE session_id = :sessionId AND type = :type ORDER BY created_at DESC")
    suspend fun getTransactionsByType(sessionId: String, type: TransactionType): List<TransactionEntity>

    @Query("SELECT * FROM transaction_entities WHERE goal_id = :goalId ORDER BY created_at DESC")
    suspend fun getTransactionsByGoal(goalId: String): List<TransactionEntity>

    @Query("SELECT SUM(amount) FROM transaction_entities WHERE session_id = :sessionId AND type = :type")
    suspend fun getTotalAmountByType(sessionId: String, type: TransactionType): Int?

    @Query("SELECT * FROM transaction_entities WHERE is_synced = 0")
    suspend fun getUnsyncedTransactions(): List<TransactionEntity>

    @Query("SELECT COUNT(*) FROM transaction_entities WHERE session_id = :sessionId")
    suspend fun getTransactionCount(sessionId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: List<TransactionEntity>)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transaction_entities WHERE player_id = :playerId ORDER BY created_at DESC")
    fun getTransactionsByPlayer(playerId: String): Flow<List<TransactionEntity>>

    @Query("DELETE FROM transaction_entities WHERE player_id = :playerId")
    suspend fun deleteTransactionsByPlayer(playerId: String)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("UPDATE transaction_entities SET is_synced = 1 WHERE transaction_id = :transactionId")
    suspend fun markTransactionAsSynced(transactionId: String)

    @Query("DELETE FROM transaction_entities WHERE session_id = :sessionId")
    suspend fun deleteTransactionsBySession(sessionId: String)

    @Query("DELETE FROM transaction_entities WHERE transaction_id = :transactionId")
    suspend fun deleteTransactionById(transactionId: String)

    // Cleanup operations
    @Query("DELETE FROM transaction_entities")
    suspend fun deleteAllTransactions()
} 