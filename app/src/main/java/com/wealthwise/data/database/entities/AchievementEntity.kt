package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "achievements",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AchievementEntity(
    @PrimaryKey
    @ColumnInfo(name = "achievement_id")
    val achievementId: String,

    @ColumnInfo(name = "user_id", index = true)
    val userId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "icon")
    val icon: String,

    @ColumnInfo(name = "is_unlocked")
    val isUnlocked: Boolean = false,

    @ColumnInfo(name = "unlocked_at")
    val unlockedAt: Date? = null,

    @ColumnInfo(name = "progress")
    val progress: Int = 0,

    @ColumnInfo(name = "target")
    val target: Int,

    @ColumnInfo(name = "reward_amount")
    val rewardAmount: Int = 0,

    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)