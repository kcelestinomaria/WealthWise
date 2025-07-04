package com.wealthwise.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wealthwise.data.model.LeaderboardEntry
import com.wealthwise.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    leaderboardEntries: List<LeaderboardEntry>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onBack: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    text = "🏆 Leaderboard",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                IconButton(
                    onClick = onRefresh,
                    enabled = !isRefreshing
                ) {
                    if (isRefreshing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            }
        }

        item {
            WealthCard(backgroundColor = MaterialTheme.colorScheme.primaryContainer) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "💰 Top Financial Masters",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "See how you stack up against other players. Build your wealth and claim your spot!",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (isLoading && leaderboardEntries.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else if (leaderboardEntries.isEmpty()) {
            item {
                WealthCard {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "🎮",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No rankings yet!",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Be the first to complete the game and set a high score!",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            itemsIndexed(leaderboardEntries) { index, entry ->
                LeaderboardEntryCard(
                    rank = index + 1,
                    entry = entry,
                    isTopThree = index < 3
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun LeaderboardEntryCard(
    rank: Int,
    entry: LeaderboardEntry,
    isTopThree: Boolean
) {
    val rankEmoji = when (rank) {
        1 -> "🥇"
        2 -> "🥈"
        3 -> "🥉"
        else -> "#$rank"
    }
    
    val backgroundColor = when (rank) {
        1 -> MaterialTheme.colorScheme.primaryContainer
        2 -> MaterialTheme.colorScheme.secondaryContainer
        3 -> MaterialTheme.colorScheme.tertiaryContainer
        else -> MaterialTheme.colorScheme.surface
    }

    WealthCard(backgroundColor = backgroundColor) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Rank
                Box(
                    modifier = Modifier.width(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = rankEmoji,
                        style = if (isTopThree) MaterialTheme.typography.headlineMedium 
                               else MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                // Player info
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = entry.playerName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${getRoleEmoji(entry.scenario)} ${entry.scenario}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            // Net worth
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatCurrency(entry.netWorth),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (entry.netWorth >= 0) MaterialTheme.colorScheme.primary
                           else MaterialTheme.colorScheme.error
                )
                Text(
                    text = "Net Worth",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        // Expandable details
        if (isTopThree) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DetailItem("💵 Cash", entry.cash)
                DetailItem("📈 Assets", entry.assets)
                if (entry.liabilities > 0) {
                    DetailItem("📉 Debt", entry.liabilities, isNegative = true)
                }
            }
        }
    }
}

@Composable
private fun DetailItem(
    label: String,
    amount: Double,
    isNegative: Boolean = false
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = formatCurrency(amount),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = if (isNegative) MaterialTheme.colorScheme.error
                   else MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun getRoleEmoji(scenario: String): String {
    return when (scenario.lowercase()) {
        "student" -> "🎓"
        "boda boda rider" -> "🏍️"
        "mama mboga" -> "🥬"
        "hustler" -> "💼"
        else -> "🎭"
    }
} 