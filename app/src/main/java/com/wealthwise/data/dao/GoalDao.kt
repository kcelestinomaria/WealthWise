package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.GoalEntity
import com.wealthwise.data.model.GoalCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Query("SELECT * FROM goals WHERE session_id = :sessionId ORDER BY priority DESC, created_at ASC")
    fun getGoalsBySessionFlow(sessionId: String): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE session_id = :sessionId AND is_completed = 0 ORDER BY priority DESC")
    suspend fun getActiveGoals(sessionId: String): List<GoalEntity>

    @Query("SELECT * FROM goals WHERE goal_id = :goalId")
    suspend fun getGoalById(goalId: String): GoalEntity?

    @Query("SELECT * FROM goals WHERE session_id = :sessionId AND category = :category")
    suspend fun getGoalsByCategory(sessionId: String, category: GoalCategory): List<GoalEntity>

    @Query("SELECT * FROM goals WHERE is_completed = 1 AND session_id = :sessionId")
    suspend fun getCompletedGoals(sessionId: String): List<GoalEntity>

    @Query("SELECT * FROM goals WHERE is_synced = 0")
    suspend fun getUnsyncedGoals(): List<GoalEntity>

    @Query("SELECT COUNT(*) FROM goals WHERE session_id = :sessionId AND is_completed = 1")
    suspend fun getCompletedGoalsCount(sessionId: String): Int

    @Query("SELECT SUM(target_amount) FROM goals WHERE session_id = :sessionId")
    suspend fun getTotalGoalAmount(sessionId: String): Int?

    @Query("SELECT SUM(current_amount) FROM goals WHERE session_id = :sessionId")
    suspend fun getTotalSavedAmount(sessionId: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalEntity): Long

    @Update
    suspend fun updateGoal(goal: GoalEntity)

    @Delete
    suspend fun deleteGoal(goal: GoalEntity)

    @Query("UPDATE goals SET current_amount = :amount, is_completed = :isCompleted WHERE goal_id = :goalId")
    suspend fun updateGoalProgress(goalId: String, amount: Int, isCompleted: Boolean)

    @Query("UPDATE goals SET is_synced = 1 WHERE goal_id = :goalId")
    suspend fun markGoalAsSynced(goalId: String)

    @Query("DELETE FROM goals WHERE goal_id = :goalId")
    suspend fun deleteGoalById(goalId: String)
}