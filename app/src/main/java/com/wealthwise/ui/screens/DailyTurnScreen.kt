package com.wealthwise.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun DailyTurnScreen(
    player: Player,
    currentEvent: TurnEvent?,
    dailyIncome: Double,
    lastDecisionResult: String?,
    onDecisionMade: (DecisionOption) -> Unit,
    onNavigateToWallet: () -> Unit,
    onNavigateToLearnCenter: () -> Unit,
    onClearResult: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Show result dialog if there's a last decision result
    lastDecisionResult?.let { result ->
        AlertDialog(
            onDismissRequest = onClearResult,
            title = { 
                Text(
                    text = "💡 Result",
                    style = MaterialTheme.typography.titleLarge
                ) 
            },
            text = { 
                Text(
                    text = result,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                TextButton(onClick = onClearResult) {
                    Text("Continue")
                }
            }
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header with day and player info
            WealthCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Day ${player.currentDay}/30",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${player.role.emoji} ${player.name}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        MoneyDisplay(
                            amount = player.calculateNetWorth(),
                            label = "Net Worth",
                            emoji = "💎",
                            color = if (player.calculateNetWorth() >= 0) 
                                     MaterialTheme.colorScheme.primary 
                                   else MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        // Daily Income Display
        if (dailyIncome > 0) {
            item {
                WealthCard(
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "💰 Daily Income",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = formatCurrency(dailyIncome),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Current Event
        currentEvent?.let { event ->
            item {
                WealthCard {
                    Column {
                        Text(
                            text = "${event.emoji} ${event.title}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Text(
                            text = event.description,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            item {
                Text(
                    text = "🤔 What will you do?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Decision Options
            items(event.options) { option ->
                DecisionButton(
                    option = option,
                    onClick = { onDecisionMade(option) }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Quick Stats
        item {
            WealthCard {
                Text(
                    text = "📊 Quick Stats",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MoneyDisplay(
                        amount = player.cash,
                        label = "Cash",
                        emoji = "💵"
                    )
                    MoneyDisplay(
                        amount = player.getTotalAssets(),
                        label = "Assets",
                        emoji = "📈"
                    )
                    MoneyDisplay(
                        amount = player.debt,
                        label = "Debt",
                        emoji = "📉",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        // Navigation Buttons
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onNavigateToWallet,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("💳 Wallet")
                }
                
                OutlinedButton(
                    onClick = onNavigateToLearnCenter,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("📚 Learn")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
} 