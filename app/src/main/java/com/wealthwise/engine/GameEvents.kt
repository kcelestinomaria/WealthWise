package com.wealthwise.engine

import com.wealthwise.data.model.*

fun getGeneralEvents(): List<TurnEvent> = listOf(
    TurnEvent(
        id = "emergency_medical",
        day = 0,
        title = "Medical Emergency! 🏥",
        description = "You need KSh 3,000 for medical expenses. How will you handle this?",
        emoji = "🏥",
        eventType = EventType.EMERGENCY,
        options = listOf(
            DecisionOption(
                id = "pay_cash",
                label = "Pay with cash",
                cost = 3000.0,
                gain = 0.0,
                consequence = "Good! You handled the emergency without debt.",
                emoji = "💰"
            ),
            DecisionOption(
                id = "use_fuliza",
                label = "Use Fuliza (high interest)",
                cost = 0.0,
                gain = 3270.0,
                consequence = "You borrowed via Fuliza. Pay back quickly!",
                emoji = "📱",
                assetType = AssetType.DEBT,
                riskLevel = RiskLevel.HIGH
            )
        )
    ),
    
    TurnEvent(
        id = "investment_mmf",
        day = 0,
        title = "MMF Investment! 📈",
        description = "A friend tells you about MMF with 8% returns. Invest?",
        emoji = "📈",
        eventType = EventType.OPPORTUNITY,
        options = listOf(
            DecisionOption(
                id = "invest_5k",
                label = "Invest KSh 5,000",
                cost = 5000.0,
                gain = 5000.0,
                consequence = "Smart! Your money will grow at 8% annually.",
                emoji = "💎",
                assetType = AssetType.MMF
            ),
            DecisionOption(
                id = "keep_cash",
                label = "Keep cash",
                cost = 0.0,
                gain = 0.0,
                consequence = "Safe but you missed a growth opportunity.",
                emoji = "💰"
            )
        )
    ),
    
    TurnEvent(
        id = "sacco_join",
        day = 0,
        title = "Join SACCO! 🏦",
        description = "Your workplace offers SACCO membership with 6% returns.",
        emoji = "🏦",
        eventType = EventType.OPPORTUNITY,
        options = listOf(
            DecisionOption(
                id = "join_sacco",
                label = "Join with KSh 3,000",
                cost = 3000.0,
                gain = 3000.0,
                consequence = "Excellent! SACCOs are safe and offer loans.",
                emoji = "🏦",
                assetType = AssetType.SACCO
            ),
            DecisionOption(
                id = "decline",
                label = "Not interested",
                cost = 0.0,
                gain = 0.0,
                consequence = "You missed a great savings opportunity.",
                emoji = "❌"
            )
        )
    )
)

fun getEventsForRole(role: Role): List<TurnEvent> = when (role) {
    Role.STUDENT -> getStudentEvents()
    Role.BODA_RIDER -> getBodaEvents()
    Role.MAMA_MBOGA -> getMbogaEvents()
    Role.HUSTLER -> getHustlerEvents()
}

fun getStudentEvents(): List<TurnEvent> = listOf(
    TurnEvent(
        id = "helb_payment",
        day = 15,
        title = "HELB Reminder 🎓",
        description = "Consider paying your HELB loan early to reduce interest.",
        emoji = "🎓",
        eventType = EventType.EDUCATION,
        options = listOf(
            DecisionOption(
                id = "pay_helb",
                label = "Pay KSh 2,000",
                cost = 2000.0,
                gain = -2000.0,
                consequence = "Smart! Early payments reduce total debt.",
                emoji = "🎓",
                assetType = AssetType.DEBT
            ),
            DecisionOption(
                id = "skip_payment",
                label = "Pay later",
                cost = 0.0,
                gain = 500.0,
                consequence = "Your debt grew by KSh 500 interest.",
                emoji = "😰",
                assetType = AssetType.DEBT
            )
        )
    )
)

fun getBodaEvents(): List<TurnEvent> = listOf(
    TurnEvent(
        id = "bike_service",
        day = 0,
        title = "Bike Service! 🏍️",
        description = "Your bike needs maintenance. Spend now or risk breakdowns?",
        emoji = "🏍️",
        eventType = EventType.EMERGENCY,
        options = listOf(
            DecisionOption(
                id = "full_service",
                label = "Full service (KSh 5,000)",
                cost = 5000.0,
                gain = 0.0,
                consequence = "Wise! Your bike will be reliable.",
                emoji = "🔧"
            ),
            DecisionOption(
                id = "skip_service",
                label = "Skip for now",
                cost = 0.0,
                gain = -1000.0,
                consequence = "Your bike broke down later!",
                emoji = "💔"
            )
        )
    )
)

fun getMbogaEvents(): List<TurnEvent> = listOf(
    TurnEvent(
        id = "wholesale_deal",
        day = 0,
        title = "Wholesale Deal! 🥬",
        description = "Buy vegetables in bulk for better margins?",
        emoji = "🥬",
        eventType = EventType.OPPORTUNITY,
        options = listOf(
            DecisionOption(
                id = "buy_bulk",
                label = "Buy KSh 8,000 worth",
                cost = 8000.0,
                gain = 12000.0,
                consequence = "Excellent! Bulk buying increased profits.",
                emoji = "📈"
            ),
            DecisionOption(
                id = "regular_purchase",
                label = "Regular purchase",
                cost = 0.0,
                gain = 0.0,
                consequence = "You missed a profit opportunity.",
                emoji = "🤷‍♀️"
            )
        )
    )
)

fun getHustlerEvents(): List<TurnEvent> = listOf(
    TurnEvent(
        id = "gig_platform",
        day = 0,
        title = "New Gig! 📱",
        description = "Join a delivery platform for better pay?",
        emoji = "📱",
        eventType = EventType.OPPORTUNITY,
        options = listOf(
            DecisionOption(
                id = "join_platform",
                label = "Join (KSh 1,000 setup)",
                cost = 1000.0,
                gain = 5000.0,
                consequence = "Great! You earned KSh 5,000 from gigs.",
                emoji = "📱"
            ),
            DecisionOption(
                id = "current_gigs",
                label = "Stick to current gigs",
                cost = 0.0,
                gain = 2000.0,
                consequence = "You earned less but avoided risk.",
                emoji = "🤷‍♂️"
            )
        )
    ),
    
    TurnEvent(
        id = "reits_investment",
        day = 20,
        title = "REITs Investment! 🏢",
        description = "Invest in real estate through REITs?",
        emoji = "🏢",
        eventType = EventType.OPPORTUNITY,
        options = listOf(
            DecisionOption(
                id = "invest_reits",
                label = "Invest KSh 8,000",
                cost = 8000.0,
                gain = 8000.0,
                consequence = "Smart diversification! REITs offer 8-12% returns.",
                emoji = "🏢",
                assetType = AssetType.REITS
            ),
            DecisionOption(
                id = "skip_reits",
                label = "Keep cash",
                cost = 0.0,
                gain = 0.0,
                consequence = "You kept cash but missed diversification.",
                emoji = "💰"
            )
        )
    )
) 