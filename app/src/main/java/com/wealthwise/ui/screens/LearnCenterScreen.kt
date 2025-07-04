package com.wealthwise.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wealthwise.ui.components.*

data class FinancialTip(
    val title: String,
    val emoji: String,
    val description: String,
    val explanation: String,
    val pros: List<String>,
    val cons: List<String>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnCenterScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tips = remember { getFinancialTips() }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = "📚 Learn Center",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(48.dp))
            }
        }

        item {
            WealthCard(backgroundColor = MaterialTheme.colorScheme.primaryContainer) {
                Text(
                    text = "💡 Master Kenyan Financial Tools",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Learn about the financial tools available in Kenya to make smarter money decisions in the game and real life!",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        items(tips) { tip ->
            FinancialTipCard(tip = tip)
        }

        item {
            WealthCard {
                Text(
                    text = "🎯 Pro Tips",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                
                ProTip("💰 Build an emergency fund before investing")
                ProTip("📊 Diversify your investments across different tools")
                ProTip("🏦 Use SACCOs for safe, long-term savings")
                ProTip("⚡ Avoid high-interest debt like Fuliza when possible")
                ProTip("📈 Start investing early, even with small amounts")
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun FinancialTipCard(tip: FinancialTip) {
    var expanded by remember { mutableStateOf(false) }

    WealthCard {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = tip.emoji,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = tip.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tip.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                TextButton(onClick = { expanded = !expanded }) {
                    Text(if (expanded) "Less" else "More")
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = tip.explanation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                if (tip.pros.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "✅ Pros:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    tip.pros.forEach { pro ->
                        Text(
                            text = "• $pro",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                        )
                    }
                }
                
                if (tip.cons.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "⚠️ Cons:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                    tip.cons.forEach { con ->
                        Text(
                            text = "• $con",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProTip(tip: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "💡",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = tip,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun getFinancialTips(): List<FinancialTip> = listOf(
    FinancialTip(
        title = "SACCOs",
        emoji = "🏦",
        description = "Savings and Credit Cooperatives",
        explanation = "SACCOs are member-owned financial cooperatives that provide savings and loan services. They typically offer 6-10% annual returns on savings and provide loans at lower interest rates than banks.",
        pros = listOf(
            "Safe and regulated savings",
            "Higher returns than regular savings accounts",
            "Access to affordable loans",
            "Dividend payments to members"
        ),
        cons = listOf(
            "Money may be locked for specific periods",
            "Withdrawal processes can take time",
            "Requires membership and regular contributions"
        )
    ),
    
    FinancialTip(
        title = "Money Market Funds (MMFs)",
        emoji = "📊",
        description = "Low-risk investment with good returns",
        explanation = "MMFs pool money from many investors to buy short-term securities. In Kenya, they typically offer 8-12% annual returns and allow easy access to your money.",
        pros = listOf(
            "Higher returns than savings accounts",
            "Relatively low risk",
            "Easy to withdraw (usually within 7 days)",
            "Professional fund management"
        ),
        cons = listOf(
            "Returns not guaranteed",
            "May have minimum investment amounts",
            "Management fees reduce returns"
        )
    ),
    
    FinancialTip(
        title = "Fuliza",
        emoji = "📱",
        description = "M-Pesa overdraft facility",
        explanation = "Fuliza is Safaricom's overdraft service that lets you complete M-Pesa transactions even when you don't have enough balance. Interest rates are high, making it expensive for long-term borrowing.",
        pros = listOf(
            "Instant access when you need money",
            "No paperwork required",
            "Helps in emergencies"
        ),
        cons = listOf(
            "Very high interest rates (up to 1% per day)",
            "Can create a debt cycle",
            "Automatic deductions from incoming money"
        )
    ),
    
    FinancialTip(
        title = "Chamas",
        emoji = "👥",
        description = "Group-based savings and investment",
        explanation = "Chamas are informal savings groups where members contribute regularly and receive lump sum payouts on rotation. They help with discipline and provide access to larger amounts for investments.",
        pros = listOf(
            "Enforces savings discipline",
            "Social support and accountability",
            "Access to lump sum amounts",
            "Can negotiate better investment rates as a group"
        ),
        cons = listOf(
            "Risk if members default",
            "Money locked until your turn",
            "Requires trust and commitment"
        )
    ),
    
    FinancialTip(
        title = "REITs",
        emoji = "🏢",
        description = "Real Estate Investment Trusts",
        explanation = "REITs allow you to invest in real estate without buying property directly. You can buy shares that represent ownership in income-generating real estate.",
        pros = listOf(
            "Diversified real estate exposure",
            "Professional property management",
            "Regular dividend income",
            "Lower entry cost than buying property"
        ),
        cons = listOf(
            "Market value can fluctuate",
            "Less control than direct property ownership",
            "Management fees"
        )
    ),
    
    FinancialTip(
        title = "Land Investment",
        emoji = "🏡",
        description = "Direct property ownership",
        explanation = "Buying land is a traditional investment in Kenya. Land values generally appreciate over time, especially in growing areas, but it requires significant capital and has costs like taxes and legal fees.",
        pros = listOf(
            "Long-term value appreciation",
            "Tangible asset you can see",
            "Can generate rental income",
            "Hedge against inflation"
        ),
        cons = listOf(
            "High entry cost",
            "Illiquid - hard to sell quickly",
            "Ongoing costs (taxes, maintenance)",
            "Market risk in some areas"
        )
    )
) 