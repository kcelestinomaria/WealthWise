package com.wealthwise.data.model

enum class Role(
    val displayName: String,
    val emoji: String,
    val description: String,
    val startingCash: Double,
    val startingDebt: Double
) {
    STUDENT(
        displayName = "Student",
        emoji = "🎓",
        description = "Start with HELB loan and small allowance. Focus on saving and reducing debt.",
        startingCash = 5000.0,
        startingDebt = 80000.0
    ),
    BODA_RIDER(
        displayName = "Boda Boda Rider",
        emoji = "🏍️",
        description = "Unstable income with fuel costs. Avoid predatory loans and build savings.",
        startingCash = 8000.0,
        startingDebt = 0.0
    ),
    MAMA_MBOGA(
        displayName = "Mama Mboga",
        emoji = "🥬",
        description = "Regular income with access to chamas. Build SACCO savings and land value.",
        startingCash = 12000.0,
        startingDebt = 0.0
    ),
    HUSTLER(
        displayName = "Hustler",
        emoji = "💼",
        description = "Random gig income. Diversify investments wisely across multiple assets.",
        startingCash = 15000.0,
        startingDebt = 0.0
    )
} 