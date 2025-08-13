package com.wealthwise.data.model

/**
 * Goal - Data model representing a financial goal
 * 
 * Used to teach users about setting specific savings targets for different purposes.
 * Each goal tracks progress from 0 to target amount with visual feedback.
 * 
 * @param id Unique identifier for database/state management
 * @param title User-friendly name (e.g., "Emergency Fund", "New Laptop")
 * @param description Detailed explanation of the goal
 * @param targetAmount Final amount to save (in Kenyan Shillings)
 * @param currentAmount Progress made toward target (starts at 0)
 * @param icon UI icon identifier for visual representation
 * @param category Grouping for different types of financial goals
 * @param isCompleted Auto-calculated when currentAmount >= targetAmount
 */
data class Goal(
    val id: String,
    val title: String,
    val description: String,
    val targetAmount: Int,           // Target savings amount
    val currentAmount: Int = 0,      // Current progress toward target
    val icon: String,
    val category: GoalCategory,
    val isCompleted: Boolean = false
) {
    /**
     * Calculate progress as percentage (0.0 to 1.0)
     * Used for progress bars and completion tracking
     */
    fun getProgressPercentage(): Float {
        return if (targetAmount > 0) {
            (currentAmount.toFloat() / targetAmount.toFloat()).coerceIn(0f, 1f)
        } else 0f
    }
}

/**
 * GoalCategory - Different types of financial goals for educational organization
 * 
 * Each category teaches different financial concepts:
 * - EMERGENCY_FUND: Financial safety and planning ahead
 * - PURCHASE: Saving for specific items vs instant gratification
 * - INVESTMENT: Growing wealth for the future
 * - TRAVEL: Fun goals that motivate saving discipline
 */
enum class GoalCategory {
    EMERGENCY_FUND,   // Safety net for unexpected expenses
    PURCHASE,         // Saving for specific items (laptop, car, etc.)
    INVESTMENT,       // Long-term wealth building goals
    TRAVEL           // Vacation and travel experiences
}

object GoalsData {
    val defaultGoals = listOf(
        Goal(
            id = "emergency_fund",
            title = "Build Emergency Fund",
            description = "Save Ksh 100,000 for emergencies",
            targetAmount = 100000,
            icon = "security",
            category = GoalCategory.EMERGENCY_FUND
        ),
        Goal(
            id = "laptop",
            title = "Buy a New Laptop",
            description = "Save Ksh 80,000 for a laptop",
            targetAmount = 80000,
            icon = "computer",
            category = GoalCategory.PURCHASE
        ),
        Goal(
            id = "investment",
            title = "Start Investing",
            description = "Invest Ksh 50,000 in stocks",
            targetAmount = 50000,
            icon = "trending_up",
            category = GoalCategory.INVESTMENT
        ),
        Goal(
            id = "vacation",
            title = "Dream Vacation",
            description = "Save Ksh 200,000 for vacation",
            targetAmount = 200000,
            icon = "flight",
            category = GoalCategory.TRAVEL
        )
    )
}