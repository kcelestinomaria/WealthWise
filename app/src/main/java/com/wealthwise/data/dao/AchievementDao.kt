package com.wealthwise.data.database.dao

import androidx.room.*
import com.wealthwise.data.database.entities.AchievementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {

    @Query("SELECT * FROM achievements WHERE user_id = :userId ORDER BY unlocked_at DESC")
    fun getAchievementsByUserFlow(userId: String): Flow<List<AchievementEntity>>

    @Query("SELECT * FROM achievements WHERE user_id = :userId AND is_unlocked = 1")
    suspend fun getUnlockedAchievements(userId: String): List<AchievementEntity>

    @Query("SELECT * FROM achievements WHERE achievement_id = :achievementId")
    suspend fun getAchievementById(achievementId: String): AchievementEntity?

    @Query("SELECT COUNT(*) FROM achievements WHERE user_id = :userId AND is_unlocked = 1")
    suspend fun getUnlockedAchievementsCount(userId: String): Int

    @Query("SELECT * FROM achievements WHERE is_synced = 0")
    suspend fun getUnsyncedAchievements(): List<AchievementEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: AchievementEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(achievements: List<AchievementEntity>)

    @Update
    suspend fun updateAchievement(achievement: AchievementEntity)

    @Delete
    suspend fun deleteAchievement(achievement: AchievementEntity)

    @Query("UPDATE achievements SET is_unlocked = 1, unlocked_at = :unlockedAt WHERE achievement_id = :achievementId")
    suspend fun unlockAchievement(achievementId: String, unlockedAt: java.util.Date)

    @Query("UPDATE achievements SET progress = :progress WHERE achievement_id = :achievementId")
    suspend fun updateAchievementProgress(achievementId: String, progress: Int)

    @Query("UPDATE achievements SET is_synced = 1 WHERE achievement_id = :achievementId")
    suspend fun markAchievementAsSynced(achievementId: String)
}