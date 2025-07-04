package com.wealthwise.data.dao

import androidx.room.*
import com.wealthwise.data.model.LeaderboardEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderboardDao {
    
    @Query("SELECT * FROM leaderboard_entries ORDER BY netWorth DESC")
    fun getAllEntries(): Flow<List<LeaderboardEntry>>
    
    @Query("SELECT * FROM leaderboard_entries ORDER BY netWorth DESC LIMIT :limit")
    fun getTopEntries(limit: Int = 10): Flow<List<LeaderboardEntry>>
    
    @Query("SELECT * FROM leaderboard_entries WHERE scenario = :scenario ORDER BY netWorth DESC LIMIT :limit")
    fun getTopEntriesByRole(scenario: String, limit: Int = 10): Flow<List<LeaderboardEntry>>
    
    @Query("SELECT * FROM leaderboard_entries WHERE syncedToFirebase = 0")
    suspend fun getUnsyncedEntries(): List<LeaderboardEntry>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: LeaderboardEntry): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntries(entries: List<LeaderboardEntry>)
    
    @Update
    suspend fun updateEntry(entry: LeaderboardEntry)
    
    @Query("UPDATE leaderboard_entries SET syncedToFirebase = 1 WHERE id = :entryId")
    suspend fun markAsSynced(entryId: Long)
    
    @Delete
    suspend fun deleteEntry(entry: LeaderboardEntry)
    
    @Query("DELETE FROM leaderboard_entries")
    suspend fun deleteAllEntries()
} 