package com.wealthwise.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val role: Role,
    val cash: Double,
    val sacco: Double,
    val mmf: Double,
    val land: Double,
    val reits: Double,
    val debt: Double,
    val currentDay: Int = 1,
    val gameCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun calculateNetWorth(): Double {
        return cash + sacco + mmf + land + reits - debt
    }
    
    fun getTotalAssets(): Double {
        return sacco + mmf + land + reits
    }
    
    fun getTotalLiabilities(): Double {
        return debt
    }
    
    companion object {
        fun createNew(name: String, role: Role): Player {
            return Player(
                name = name,
                role = role,
                cash = role.startingCash,
                sacco = 0.0,
                mmf = 0.0,
                land = 0.0,
                reits = 0.0,
                debt = role.startingDebt
            )
        }
    }
} 