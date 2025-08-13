package com.wealthwise.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val playerId: Long,
    val day: Int,
    val type: TransactionType,
    val amount: Double,
    val description: String,
    val timestamp: Long = System.currentTimeMillis()
)

enum class TransactionType {
    INCOME,
    BONUS,
    BILL_PAYMENT,
    EXPENSE,
    INVESTMENT,
    LOAN,
    SAVINGS,
    WITHDRAWAL,
    ASSET_PURCHASE,
    ASSET_SALE,
    DEBT_PAYMENT,
    INTEREST_EARNED,
    INTEREST_PAID,
    GOAL_CONTRIBUTION
} 