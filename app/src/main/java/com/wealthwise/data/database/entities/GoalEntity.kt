package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.wealthwise.data.model.GoalCategory
import java.util.*

@Entity(
    tableName = "goals",
    foreignKeys = [
        ForeignKey(
            entity = GameSessionEntity::class,
            parentColumns = ["session_id"],
            childColumns = ["session_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GoalEntity(
    @PrimaryKey
    @ColumnInfo(name = "goal_id")
    val goalId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "session_id", index = true)
    val sessionId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "target_amount")
    val targetAmount: Int,

    @ColumnInfo(name = "current_amount")
    val currentAmount: Int = 0,

    @ColumnInfo(name = "category")
    val category: GoalCategory,

    @ColumnInfo(name = "icon")
    val icon: String,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),

    @ColumnInfo(name = "completed_at")
    val completedAt: Date? = null,

    @ColumnInfo(name = "priority")
    val priority: Int = 0,

    @ColumnInfo(name = "deadline")
    val deadline: Date? = null,

    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)