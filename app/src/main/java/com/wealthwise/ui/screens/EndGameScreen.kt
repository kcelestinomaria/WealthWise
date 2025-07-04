package com.wealthwise.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wealthwise.data.model.Player
import com.wealthwise.ui.components.*
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndGameScreen(
    player: Player,
    onPlayAgain: () -> Unit,
    onViewLeaderboard: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val netWorth = player.calculateNetWorth()
    val totalAssets = player.cash + player.getTotalAssets()
    
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            // Congratulations Header
            WealthCard(backgroundColor = MaterialTheme.colorScheme.primaryContainer) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "🎉 Game Complete!",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Well done, ${player.name}!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "You completed 30 days as a ${player.role.displayName}",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            // Final Net Worth
            WealthCard(
                backgroundColor = if (netWorth >= 0) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.errorContainer
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "💎 Final Net Worth",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = formatCurrency(netWorth),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (netWorth >= 0) MaterialTheme.colorScheme.primary
                               else MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        item {
            // Detailed Breakdown
            WealthCard {
                Text(
                    text = "💰 Financial Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                BreakdownRow("💵 Cash", player.cash)
                if (player.sacco > 0) BreakdownRow("🏦 SACCO", player.sacco)
                if (player.mmf > 0) BreakdownRow("📊 MMF", player.mmf)
                if (player.land > 0) BreakdownRow("🏡 Land", player.land)
                if (player.reits > 0) BreakdownRow("🏢 REITs", player.reits)
                
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                
                BreakdownRow("📈 Total Assets", totalAssets, isTotal = true)
                if (player.debt > 0) {
                    BreakdownRow("📉 Total Debt", player.debt, isNegative = true)
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                
                BreakdownRow(
                    "💎 Net Worth", 
                    netWorth, 
                    isTotal = true,
                    color = if (netWorth >= 0) MaterialTheme.colorScheme.primary
                           else MaterialTheme.colorScheme.error
                )
            }
        }

        item {
            // Performance Message
            WealthCard {
                Text(
                    text = getPerformanceMessage(netWorth, player.role.displayName),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            // Action Buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                GameButton(
                    text = "Play Again",
                    emoji = "🔄",
                    onClick = onPlayAgain
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onViewLeaderboard,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("🏆 Leaderboard")
                    }
                    
                    OutlinedButton(
                        onClick = onBackToHome,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("🏠 Home")
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun BreakdownRow(
    label: String,
    amount: Double,
    isTotal: Boolean = false,
    isNegative: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onSurface
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
private fun WealthPieChart(
    cash: Double,
    sacco: Double,
    mmf: Double,
    land: Double,
    reits: Double,
    debt: Double,
    modifier: Modifier = Modifier
) {
    val total = cash + sacco + mmf + land + reits + debt
    
    if (total <= 0) return
    
    val values = listOf(
        "Cash" to cash,
        "SACCO" to sacco,
        "MMF" to mmf,
        "Land" to land,
        "REITs" to reits,
        "Debt" to debt
    ).filter { it.second > 0 }
    
    val colors = listOf(
        Color(0xFF4CAF50), // Green
        Color(0xFF2196F3), // Blue
        Color(0xFFFF9800), // Orange
        Color(0xFF9C27B0), // Purple
        Color(0xFF00BCD4), // Cyan
        Color(0xFFF44336)  // Red for debt
    )
    
    Canvas(modifier = modifier) {
        drawPieChart(values, colors, total)
    }
}

private fun DrawScope.drawPieChart(
    values: List<Pair<String, Double>>,
    colors: List<Color>,
    total: Double
) {
    val center = androidx.compose.ui.geometry.Offset(size.width / 2, size.height / 2)
    val radius = size.minDimension / 2.5f
    
    var startAngle = 0f
    
    values.forEachIndexed { index, (_, value) ->
        val sweepAngle = (value / total * 360).toFloat()
        val color = colors.getOrElse(index) { colors.first() }
        
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = androidx.compose.ui.geometry.Offset(
                center.x - radius,
                center.y - radius
            ),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
        )
        
        // Draw border
        drawArc(
            color = Color.White,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = 4.dp.toPx()),
            topLeft = androidx.compose.ui.geometry.Offset(
                center.x - radius,
                center.y - radius
            ),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
        )
        
        startAngle += sweepAngle
    }
}

private fun getPerformanceMessage(netWorth: Double, role: String): String {
    return when {
        netWorth >= 100000 -> "🌟 Outstanding! You've mastered financial management as a $role!"
        netWorth >= 50000 -> "💪 Excellent work! You've built solid wealth as a $role!"
        netWorth >= 20000 -> "👍 Good job! You've grown your money as a $role!"
        netWorth >= 0 -> "📚 Not bad! You finished with positive net worth as a $role!"
        netWorth >= -20000 -> "💡 Keep learning! Review the Learn Center and try different strategies!"
        else -> "🎯 Don't give up! Financial literacy takes practice. Play again!"
    }
} 