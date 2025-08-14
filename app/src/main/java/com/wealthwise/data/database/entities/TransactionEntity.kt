package com.wealthwise.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.wealthwise.data.model.TransactionType
import java.util.*


@Entity(
    tableName = "transaction_entities",
//    foreignKeys = [
//        ForeignKey(
//            entity = GameSessionEntity::class,
//            parentColumns = ["session_id"],
//            childColumns = ["session_id"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
)
data class TransactionEntity(
    @PrimaryKey
    @ColumnInfo(name = "transaction_id")
    val transactionId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "session_id", index = true)
    val sessionId: String,

    @ColumnInfo(name = "player_id", index = true)
    val playerId: String,

    @ColumnInfo(name = "type")
    val type: TransactionType,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "category")
    val category: String? = null,

    @ColumnInfo(name = "day")
    val day: Int,

    @ColumnInfo(name = "balance_before")
    val balanceBefore: Int,

    @ColumnInfo(name = "balance_after")
    val balanceAfter: Int,

    @ColumnInfo(name = "goal_id")
    val goalId: String? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),

    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false
)
