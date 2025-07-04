package com.wealthwise.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard_entries")
data class LeaderboardEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val playerName: String,
    val netWorth: Double,
    val scenario: String, // Role name
    val cash: Double,
    val assets: Double,
    val liabilities: Double,
    val date: Long,
    val rank: Int = 0,
    val syncedToFirebase: Boolean = false
) {
    companion object {
        fun fromPlayer(player: Player): LeaderboardEntry {
            return LeaderboardEntry(
                playerName = player.name,
                netWorth = player.calculateNetWorth(),
                scenario = player.role.displayName,
                cash = player.cash,
                assets = player.getTotalAssets(),
                liabilities = player.getTotalLiabilities(),
                date = System.currentTimeMillis()
            )
        }
    }
} 