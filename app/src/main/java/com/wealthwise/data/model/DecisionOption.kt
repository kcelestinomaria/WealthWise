package com.wealthwise.data.model

data class DecisionOption(
    val id: String,
    val label: String,
    val cost: Double,
    val gain: Double,
    val consequence: String,
    val emoji: String = "💰",
    val assetType: AssetType? = null,
    val riskLevel: RiskLevel = RiskLevel.LOW
)

enum class AssetType {
    CASH,
    SACCO,
    MMF,
    LAND,
    REITS,
    DEBT
}

enum class RiskLevel {
    LOW,
    MEDIUM,
    HIGH
} 