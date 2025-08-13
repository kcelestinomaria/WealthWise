package com.wealthwise.data.model

enum class DailyEventType {
    INCOME, EXPENSE, OPPORTUNITY
}

data class DailyEvent(
    val id: Int,
    val type: DailyEventType,
    val title: String,
    val description: String,
    val icon: String,
    val color: String,
    val bgColor: String
)

object EventsData {
    val sampleEvents = listOf(
        DailyEvent(
            id = 1,
            type = DailyEventType.INCOME,
            title = "Salary Received!",
            description = "Your monthly salary has been deposited",
            icon = "attach_money",
            color = "#10B981",
            bgColor = "#DCFCE7"
        ),
        DailyEvent(
            id = 2,
            type = DailyEventType.EXPENSE,
            title = "Rent Due",
            description = "Monthly rent payment of Ksh 18,000 is due tomorrow",
            icon = "home",
            color = "#F59E0B",
            bgColor = "#FEF3C7"
        ),
        DailyEvent(
            id = 3,
            type = DailyEventType.OPPORTUNITY,
            title = "Investment Opportunity",
            description = "A new mutual fund with 8% annual returns is available",
            icon = "trending_up",
            color = "#3B82F6",
            bgColor = "#DBEAFE"
        )
    )
}