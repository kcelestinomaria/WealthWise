package com.wealthwise

import com.wealthwise.data.model.*
import com.wealthwise.engine.GameEngine
import org.junit.Test
import org.junit.Assert.*

class GameEngineTest {

    @Test
    fun testPlayerNetWorthCalculation() {
        val player = Player(
            id = 1,
            name = "Test Player",
            role = Role.STUDENT,
            cash = 5000.0,
            sacco = 10000.0,
            mmf = 15000.0,
            land = 50000.0,
            reits = 20000.0,
            debt = 25000.0,
            currentDay = 1
        )

        val expectedNetWorth = 75000.0 // 5k + 10k + 15k + 50k + 20k - 25k
        assertEquals(expectedNetWorth, player.calculateNetWorth(), 0.01)
    }

    @Test
    fun testPlayerTotalAssets() {
        val player = Player(
            id = 1,
            name = "Test Player",
            role = Role.STUDENT,
            cash = 5000.0,
            sacco = 10000.0,
            mmf = 15000.0,
            land = 50000.0,
            reits = 20000.0,
            debt = 25000.0,
            currentDay = 1
        )

        val expectedAssets = 95000.0 // 10k + 15k + 50k + 20k (excludes cash)
        assertEquals(expectedAssets, player.getTotalAssets(), 0.01)
    }

    @Test
    fun testDailyIncomeGeneration() {
        val studentIncome = GameEngine.generateDailyIncome(Role.STUDENT, 1)
        val bodaIncome = GameEngine.generateDailyIncome(Role.BODA_BODA_RIDER, 1)
        
        assertTrue("Student income should be positive", studentIncome >= 0)
        assertTrue("Boda income should be positive", bodaIncome >= 0)
        assertTrue("Boda income should be higher than student", bodaIncome >= studentIncome)
    }

    @Test
    fun testCashDecision() {
        val player = Player(
            id = 1,
            name = "Test Player",
            role = Role.STUDENT,
            cash = 5000.0,
            currentDay = 1
        )

        val option = DecisionOption(
            label = "Buy Item",
            emoji = "🛒",
            cost = 2000.0,
            gain = 0.0,
            consequence = "Purchase made",
            assetType = AssetType.CASH
        )

        val result = GameEngine.processDecision(player, option)
        
        assertEquals(3000.0, result.updatedPlayer.cash, 0.01)
        assertEquals(TransactionType.EXPENSE, result.transaction.type)
    }

    @Test
    fun testSACCOInvestment() {
        val player = Player(
            id = 1,
            name = "Test Player",
            role = Role.STUDENT,
            cash = 5000.0,
            sacco = 10000.0,
            currentDay = 1
        )

        val option = DecisionOption(
            label = "SACCO Deposit",
            emoji = "🏦",
            cost = 2000.0,
            gain = 0.0,
            consequence = "Invested in SACCO",
            assetType = AssetType.SACCO
        )

        val result = GameEngine.processDecision(player, option)
        
        assertEquals(3000.0, result.updatedPlayer.cash, 0.01)
        assertEquals(12000.0, result.updatedPlayer.sacco, 0.01)
        assertEquals(TransactionType.INVESTMENT, result.transaction.type)
    }

    @Test
    fun testGameCompletion() {
        val player = Player(
            id = 1,
            name = "Test Player",
            role = Role.STUDENT,
            currentDay = 29
        )

        val option = DecisionOption(
            label = "Final Decision",
            emoji = "🎯",
            cost = 0.0,
            gain = 0.0,
            consequence = "Game ends",
            assetType = AssetType.CASH
        )

        val result = GameEngine.processDecision(player, option)
        
        assertEquals(30, result.updatedPlayer.currentDay)
        assertTrue(result.updatedPlayer.gameCompleted)
    }

    @Test
    fun testEventGeneration() {
        val event = GameEngine.generateDailyEvent(1, Role.STUDENT)
        
        assertNotNull(event)
        assertTrue(event.options.isNotEmpty())
        assertFalse(event.title.isEmpty())
        assertFalse(event.description.isEmpty())
    }

    @Test
    fun testPlayerCreation() {
        val player = Player.createNew("John Doe", Role.STUDENT)
        
        assertEquals("John Doe", player.name)
        assertEquals(Role.STUDENT, player.role)
        assertEquals(Role.STUDENT.startingCash, player.cash, 0.01)
        assertEquals(Role.STUDENT.startingDebt, player.debt, 0.01)
        assertEquals(1, player.currentDay)
        assertFalse(player.gameCompleted)
    }

    @Test
    fun testNegativeNetWorth() {
        val player = Player(
            id = 1,
            name = "Test Player",
            role = Role.STUDENT,
            cash = 1000.0,
            debt = 50000.0,
            currentDay = 1
        )

        assertEquals(-49000.0, player.calculateNetWorth(), 0.01)
    }

    @Test
    fun testLeaderboardEntry() {
        val player = Player(
            id = 1,
            name = "Test Player",
            role = Role.STUDENT,
            cash = 5000.0,
            sacco = 10000.0,
            debt = 2000.0,
            currentDay = 30,
            gameCompleted = true
        )

        val entry = LeaderboardEntry.fromPlayer(player)
        
        assertEquals(player.name, entry.playerName)
        assertEquals(player.role.displayName, entry.scenario)
        assertEquals(player.calculateNetWorth(), entry.netWorth, 0.01)
    }
} 