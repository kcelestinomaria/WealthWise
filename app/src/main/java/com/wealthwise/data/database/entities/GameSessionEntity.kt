package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.wealthwise.data.model.Goal
import java.util.*

@Entity(
    tableName = "game_sessions",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GameSessionEntity(
    @PrimaryKey
    @ColumnInfo(name = "session_id")
    val sessionId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "user_id", index = true)
    val userId: String,

    @ColumnInfo(name = "current_day")
    val currentDay: Int = 1,

    @ColumnInfo(name = "balance")
    val balance: Int = 0,

    @ColumnInfo(name = "savings")
    val savings: Int = 0,

    @ColumnInfo(name = "debt")
    val debt: Int = 0,

    @ColumnInfo(name = "monthly_income")
    val monthlyIncome: Int = 45000,

    @ColumnInfo(name = "monthly_expenses")
    val monthlyExpenses: Int = 35000,

    @ColumnInfo(name = "career")
    val career: String = "Teacher",

    @ColumnInfo(name = "career_id")
    val careerId: String? = null,

    @ColumnInfo(name = "goals")
    val goals: List<Goal> = emptyList(),

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,

    @ColumnInfo(name = "completion_score")
    val completionScore: Int = 0,

    @ColumnInfo(name = "started_at")
    val startedAt: Date = Date(),

    @ColumnInfo(name = "completed_at")
    val completedAt: Date? = null,

    @ColumnInfo(name = "last_played_at")
    val lastPlayedAt: Date = Date(),

    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)