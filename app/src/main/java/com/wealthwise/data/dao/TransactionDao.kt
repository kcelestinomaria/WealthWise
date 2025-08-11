package com.wealthwise.data.dao

import androidx.room.*
import com.wealthwise.data.model.Transaction
import com.wealthwise.data.model.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    
    @Query("SELECT * FROM transactions WHERE playerId = :playerId ORDER BY day DESC, timestamp DESC")
    fun getTransactionsByPlayer(playerId: Long): Flow<List<Transaction>>
    
    @Query("SELECT * FROM transactions WHERE playerId = :playerId AND day = :day ORDER BY timestamp DESC")
    fun getTransactionsByPlayerAndDay(playerId: Long, day: Int): Flow<List<Transaction>>
    
    @Query("SELECT * FROM transactions WHERE playerId = :playerId AND type = :type ORDER BY timestamp DESC")
    fun getTransactionsByPlayerAndType(playerId: Long, type: TransactionType): Flow<List<Transaction>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: List<Transaction>)
    
    @Update
    suspend fun updateTransaction(transaction: Transaction)
    
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
    
    @Query("DELETE FROM transactions WHERE playerId = :playerId")
    suspend fun deleteTransactionsByPlayer(playerId: Long)
    
    @Query("DELETE FROM transactions")
    suspend fun deleteAllTransactions()
} 