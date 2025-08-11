package com.wealthwise.data.model

data class TurnEvent(
    val id: String,
    val day: Int,
    val title: String,
    val description: String,
    val options: List<DecisionOption>,
    val emoji: String = "📅",
    val eventType: EventType = EventType.GENERAL,
    val roleSpecific: Role? = null
)

enum class EventType {
    INCOME,
    EMERGENCY,
    OPPORTUNITY,
    EDUCATION,
    GENERAL,
    ROLE_SPECIFIC
} 