package com.wealthwise.data.model


/**
 * GameState data class - Core game state that tracks all player progress
 *
 * This represents the complete financial simulation state including:
 * - Current game day (time progression)
 * - Financial balances (liquid cash, savings, debt)
 * - Career and income information
 * - Goals and achievements
 * - Game progression flags
 */
data class GameState(
    // Game progression
    val currentDay: Int = 1,
    val isOnboardingCompleted: Boolean = false,

    // Financial state
    val balance: Int = 0,           // Liquid cash available
    val savings: Int = 0,           // Money in savings/investments
    val debt: Int = 0,              // Outstanding debt/loans

    // Career and income
    val career: String = "",
    val monthlyIncome: Int = 0,
    val monthlyExpenses: Int = 0,
    val selectedCareerId: String = "",

    // Goals and achievements
    val goals: List<Goal> = emptyList(),
    val unlockedAchievements: List<String> = emptyList(),

    // Game settings and metadata
    val difficultyLevel: String = "NORMAL",
    val lastPlayedAt: Long = System.currentTimeMillis(),
    val totalPlayTime: Long = 0,

    // Statistics tracking
    val totalIncome: Int = 0,
    val totalExpenses: Int = 0,
    val totalInvestments: Int = 0,
    val totalDebtTaken: Int = 0,
    val goalsCompleted: Int = 0
) {
    /**
     * Calculate net worth (assets minus liabilities)
     * Net Worth = Balance + Savings - Debt
     */
    fun getNetWorth(): Int {
        return balance + savings - debt
    }

    /**
     * Calculate total assets (before considering debt)
     */
    fun getTotalAssets(): Int {
        return balance + savings
    }

    /**
     * Check if player can afford a specific amount
     */
    fun canAfford(amount: Int): Boolean {
        return balance >= amount
    }

    /**
     * Get financial health score (0-100)
     * Based on debt-to-income ratio, savings rate, etc.
     */
    fun getFinancialHealthScore(): Int {
        if (monthlyIncome <= 0) return 0

        val debtToIncomeRatio = if (monthlyIncome > 0) debt.toFloat() / (monthlyIncome * 12) else 0f
        val savingsRate = if (monthlyIncome > 0) savings.toFloat() / (monthlyIncome * 12) else 0f
        val liquidityRatio = if (monthlyExpenses > 0) balance.toFloat() / monthlyExpenses else 0f

        var score = 100

        // Penalize high debt
        if (debtToIncomeRatio > 0.4f) score -= 30
        else if (debtToIncomeRatio > 0.2f) score -= 15

        // Reward good savings
        if (savingsRate > 0.2f) score += 10
        else if (savingsRate < 0.05f) score -= 20

        // Reward emergency fund (3+ months expenses)
        if (liquidityRatio >= 3f) score += 15
        else if (liquidityRatio < 1f) score -= 25

        return score.coerceIn(0, 100)
    }

    /**
     * Get current month based on game day (assuming 30 days per month)
     */
    fun getCurrentMonth(): Int {
        return ((currentDay - 1) / 30) + 1
    }

    /**
     * Check if it's time for monthly income (every 30 days)
     */
    fun isPayday(): Boolean {
        return currentDay % 30 == 1 && currentDay > 1
    }

    /**
     * Get active (incomplete) goals
     */
    fun getActiveGoals(): List<Goal> {
        return goals.filter { !it.isCompleted }
    }

    /**
     * Get completed goals
     */
    fun getCompletedGoals(): List<Goal> {
        return goals.filter { it.isCompleted }
    }

    /**
     * Calculate total amount invested in goals
     */
    fun getTotalGoalInvestments(): Int {
        return goals.sumOf { it.currentAmount }
    }
}


