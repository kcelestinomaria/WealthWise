package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.GameSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSessionDao {

    @Query("SELECT * FROM game_sessions WHERE user_id = :userId ORDER BY last_played_at DESC")
    fun getSessionsByUserFlow(userId: String): Flow<List<GameSessionEntity>>

    @Query("SELECT * FROM game_sessions WHERE user_id = :userId AND is_completed = 0 LIMIT 1")
    suspend fun getCurrentSession(userId: String): GameSessionEntity?

    @Query("SELECT * FROM game_sessions WHERE user_id = :userId AND is_completed = 0 LIMIT 1")
    fun getCurrentSessionFlow(userId: String): Flow<GameSessionEntity?>

    @Query("SELECT * FROM game_sessions WHERE session_id = :sessionId")
    suspend fun getSessionById(sessionId: String): GameSessionEntity?

    @Query("SELECT * FROM game_sessions WHERE is_completed = 1 ORDER BY completion_score DESC LIMIT :limit")
    suspend fun getTopCompletedSessions(limit: Int = 10): List<GameSessionEntity>

    @Query("SELECT * FROM game_sessions WHERE is_synced = 0")
    suspend fun getUnsyncedSessions(): List<GameSessionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: GameSessionEntity): Long

    @Update
    suspend fun updateSession(session: GameSessionEntity)

    @Delete
    suspend fun deleteSession(session: GameSessionEntity)

    @Query("UPDATE game_sessions SET is_synced = 1 WHERE session_id = :sessionId")
    suspend fun markSessionAsSynced(sessionId: String)

    @Query("UPDATE game_sessions SET last_played_at = :timestamp WHERE session_id = :sessionId")
    suspend fun updateLastPlayed(sessionId: String, timestamp: java.util.Date)

    @Query("DELETE FROM game_sessions WHERE session_id = :sessionId")
    suspend fun deleteSessionById(sessionId: String)
}