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
import com.wealthwise.data.model.*
import com.wealthwise.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    player: Player,
    transactions: List<Transaction>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "💳 Your Wallet",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(48.dp)) // Balance the IconButton
            }
        }

        item {
            // Net Worth Summary
            WealthCard(
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "💎 Net Worth",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = formatCurrency(player.calculateNetWorth()),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (player.calculateNetWorth() >= 0) 
                                MaterialTheme.colorScheme.primary
                              else MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Day ${player.currentDay}/30 • ${player.role.emoji} ${player.role.displayName}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            // Cash
            WealthCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "💵 Cash",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Available for spending",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = formatCurrency(player.cash),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        item {
            // Assets Section
            Text(
                text = "📈 Assets",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            // SACCO
            AssetRow(
                title = "🏦 SACCO",
                description = "Safe savings with loan access",
                amount = player.sacco
            )
        }

        item {
            // MMF
            AssetRow(
                title = "📊 Money Market Fund",
                description = "8% annual returns",
                amount = player.mmf
            )
        }

        item {
            // Land
            AssetRow(
                title = "🏡 Land",
                description = "High-value asset",
                amount = player.land
            )
        }

        item {
            // REITs
            AssetRow(
                title = "🏢 REITs",
                description = "Real estate investments",
                amount = player.reits
            )
        }

        if (player.debt > 0) {
            item {
                // Debt Section
                Text(
                    text = "📉 Debt",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            }

            item {
                // Debt
                WealthCard(
                    backgroundColor = MaterialTheme.colorScheme.errorContainer
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "💳 Total Debt",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Pay down to improve net worth",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = formatCurrency(player.debt),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        item {
            // Summary
            WealthCard {
                Text(
                    text = "📋 Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                
                SummaryRow("Total Assets", player.getTotalAssets() + player.cash)
                SummaryRow("Total Liabilities", player.debt, isNegative = true)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                SummaryRow(
                    "Net Worth", 
                    player.calculateNetWorth(), 
                    isTotal = true,
                    color = if (player.calculateNetWorth() >= 0) 
                            MaterialTheme.colorScheme.primary
                          else MaterialTheme.colorScheme.error
                )
            }
        }

        // Recent Transactions
        if (transactions.isNotEmpty()) {
            item {
                Text(
                    text = "📋 Recent Transactions",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            items(transactions.take(5)) { transaction ->
                TransactionCard(transaction = transaction)
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun AssetRow(
    title: String,
    description: String,
    amount: Double
) {
    if (amount > 0) {
        WealthCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = formatCurrency(amount),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    amount: Double,
    isNegative: Boolean = false,
    isTotal: Boolean = false,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = if (isNegative) "-${formatCurrency(amount)}" else formatCurrency(amount),
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.SemiBold,
            color = color
        )
    }
    if (!isTotal) {
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun TransactionCard(
    transaction: Transaction
) {
    WealthCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Day ${transaction.day} • ${transaction.type.name}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = formatCurrency(transaction.amount),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (transaction.amount >= 0) MaterialTheme.colorScheme.primary 
                       else MaterialTheme.colorScheme.error
            )
        }
    }
} 