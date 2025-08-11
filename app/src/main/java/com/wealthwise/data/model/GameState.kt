package com.wealthwise.data.model

/**
 * GameState - Core data model representing the player's financial situation
 * 
 * This is the heart of the financial literacy game, tracking all player progress
 * and financial metrics. Updated by ViewModel actions (pay bills, invest, etc.)
 * and persisted to local database for offline functionality.
 * 
 * Financial Education Concepts:
 * - Balance: Liquid cash available for spending
 * - Savings: Money invested/saved for growth
 * - Debt: Money owed (reduces net worth)
 * - Net Worth: True financial position (balance + savings - debt)
 * - Monthly Cash Flow: Income vs expenses (budgeting lesson)
 */
data class GameState(
    val currentDay: Int = 1,                              // Game time progression
    val balance: Int = 0,                                 // Liquid cash in hand
    val savings: Int = 0,                                 // Invested/saved money
    val debt: Int = 0,                                    // Outstanding loans
    val monthlyIncome: Int = 45000,                       // Salary from career
    val career: String = "Teacher",                       // Selected profession
    val monthlyExpenses: Int = 35000,                     // Fixed monthly costs
    val isOnboardingCompleted: Boolean = false,           // Tutorial completed
    val selectedCareerId: String? = null,                 // Career selection ID
    val goals: List<Goal> = GoalsData.defaultGoals        // Financial goals list
) {
    /**
     * Calculate Net Worth - fundamental financial literacy concept
     * Formula: Assets (balance + savings) - Liabilities (debt)
     * 
     * This teaches players that true wealth isn't just cash in hand,
     * but total assets minus what you owe.
     */
    fun getNetWorth(): Int = balance + savings - debt
}