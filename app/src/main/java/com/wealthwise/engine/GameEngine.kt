package com.wealthwise.engine

import com.wealthwise.data.model.*
import kotlin.random.Random

object GameEngine {
    
    fun processDecision(player: Player, option: DecisionOption): GameResult {
        val updatedPlayer = applyDecisionToPlayer(player, option)
        val transaction = createTransaction(player.id, player.currentDay, option)
        
        return GameResult(
            updatedPlayer = updatedPlayer,
            transaction = transaction,
            message = option.consequence
        )
    }
    
    private fun applyDecisionToPlayer(player: Player, option: DecisionOption): Player {
        var newCash = player.cash
        var newSacco = player.sacco
        var newMmf = player.mmf
        var newLand = player.land
        var newReits = player.reits
        var newDebt = player.debt
        
        // Apply costs
        newCash -= option.cost
        
        // Apply gains based on asset type
        when (option.assetType) {
            AssetType.CASH -> newCash += option.gain
            AssetType.SACCO -> newSacco += option.gain
            AssetType.MMF -> newMmf += option.gain
            AssetType.LAND -> newLand += option.gain
            AssetType.REITS -> newReits += option.gain
            AssetType.DEBT -> newDebt += option.gain
            null -> newCash += option.gain
        }
        
        return player.copy(
            cash = newCash,
            sacco = newSacco,
            mmf = newMmf,
            land = newLand,
            reits = newReits,
            debt = newDebt,
            currentDay = player.currentDay + 1,
            gameCompleted = player.currentDay >= 30
        )
    }
    
    private fun createTransaction(
        playerId: Long,
        day: Int,
        option: DecisionOption
    ): Transaction {
        val type = when (option.assetType) {
            AssetType.CASH -> if (option.gain > 0) TransactionType.INCOME else TransactionType.EXPENSE
            AssetType.SACCO, AssetType.MMF, AssetType.REITS, AssetType.LAND -> TransactionType.INVESTMENT
            AssetType.DEBT -> if (option.gain > 0) TransactionType.LOAN else TransactionType.DEBT_PAYMENT
            null -> if (option.gain > 0) TransactionType.INCOME else TransactionType.EXPENSE
        }
        
        return Transaction(
            playerId = playerId,
            day = day,
            type = type,
            amount = if (option.cost > 0) -option.cost else option.gain,
            description = option.label
        )
    }
    
    fun generateDailyEvent(day: Int, role: Role): TurnEvent {
        val events = getEventsForRole(role) + getGeneralEvents()
        val daySpecificEvents = events.filter { it.day == 0 || it.day == day }
        
        return if (daySpecificEvents.isNotEmpty()) {
            daySpecificEvents.random()
        } else {
            events.random()
        }.copy(day = day)
    }
    
    fun generateDailyIncome(role: Role, day: Int): Double {
        return when (role) {
            Role.STUDENT -> {
                if (day % 7 == 0) 2000.0 // Weekly allowance
                else 0.0
            }
            Role.BODA_RIDER -> {
                Random.nextDouble(500.0, 2500.0) // Variable daily income
            }
            Role.MAMA_MBOGA -> {
                Random.nextDouble(800.0, 1800.0) // Steady business income
            }
            Role.HUSTLER -> {
                if (Random.nextDouble() < 0.3) Random.nextDouble(3000.0, 8000.0) // Big gig
                else Random.nextDouble(200.0, 1000.0) // Small gig
            }
        }
    }
}

data class GameResult(
    val updatedPlayer: Player,
    val transaction: Transaction,
    val message: String
) 