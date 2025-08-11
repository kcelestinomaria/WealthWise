package com.wealthwise.data.dao

import androidx.room.*
import com.wealthwise.data.model.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    
    @Query("SELECT * FROM players ORDER BY createdAt DESC")
    fun getAllPlayers(): Flow<List<Player>>
    
    @Query("SELECT * FROM players WHERE id = :playerId")
    fun getPlayerById(playerId: Long): Flow<Player?>
    
    @Query("SELECT * FROM players WHERE gameCompleted = 0 ORDER BY createdAt DESC LIMIT 1")
    fun getCurrentPlayer(): Flow<Player?>
    
    @Query("SELECT * FROM players WHERE gameCompleted = 1 ORDER BY createdAt DESC")
    fun getCompletedPlayers(): Flow<List<Player>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player): Long
    
    @Update
    suspend fun updatePlayer(player: Player)
    
    @Delete
    suspend fun deletePlayer(player: Player)
    
    @Query("DELETE FROM players WHERE id = :playerId")
    suspend fun deletePlayerById(playerId: Long)
    
    @Query("DELETE FROM players")
    suspend fun deleteAllPlayers()
} 