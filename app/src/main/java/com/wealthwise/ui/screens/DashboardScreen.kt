package com.wealthwise.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wealthwise.data.model.DailyEvent
import com.wealthwise.data.model.EventsData
import com.wealthwise.data.model.GameState
import com.wealthwise.ui.theme.*
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    gameState: GameState,
    onPayBills: () -> Unit,
    onInvest: () -> Unit,
    onTakeLoan: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Component
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Financial Dashboard",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Gray900
                        )
                        Text(
                            text = "Day ${gameState.currentDay}",
                            fontSize = 14.sp,
                            color = Gray600
                        )
                    }
                    
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Gray600
                        )
                    }
                }
            }
        }

        // Financial Summary Component
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Financial Summary",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Metrics Grid
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            FinancialMetric(
                                modifier = Modifier.weight(1f),
                                icon = Icons.Default.AccountBalanceWallet,
                                iconColor = androidx.compose.ui.graphics.Color.White,
                                iconBackground = Emerald500,
                                label = "Balance",
                                value = "Ksh ${numberFormat.format(gameState.balance)}",
                                valueColor = if (gameState.balance >= 0) Emerald600 else Red500
                            )
                            
                            FinancialMetric(
                                modifier = Modifier.weight(1f),
                                icon = Icons.Default.AccountBalance,
                                iconColor = androidx.compose.ui.graphics.Color.White,
                                iconBackground = Sky500,
                                label = "Income",
                                value = "Ksh ${numberFormat.format(gameState.monthlyIncome)}/mo",
                                valueColor = Sky600
                            )
                        }
                        
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            FinancialMetric(
                                modifier = Modifier.weight(1f),
                                icon = Icons.Default.CreditCard,
                                iconColor = androidx.compose.ui.graphics.Color.White,
                                iconBackground = Red500,
                                label = "Debt",
                                value = "Ksh ${numberFormat.format(gameState.debt)}",
                                valueColor = Red500
                            )
                            
                            FinancialMetric(
                                modifier = Modifier.weight(1f),
                                icon = Icons.Default.TrendingUp,
                                iconColor = androidx.compose.ui.graphics.Color.White,
                                iconBackground = Purple500,
                                label = "Net Worth",
                                value = "Ksh ${numberFormat.format(gameState.getNetWorth())}",
                                valueColor = if (gameState.getNetWorth() >= 0) Emerald600 else Red500
                            )
                        }
                    }
                }
            }
        }

        // Action Buttons Component
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Payment,
                    label = "Pay Bills",
                    gradient = listOf(Orange400, Orange500),
                    onClick = onPayBills
                )
                
                ActionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.TrendingUp,
                    label = "Invest",
                    gradient = listOf(Emerald500, Emerald600),
                    onClick = onInvest
                )
                
                ActionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.AccountBalance,
                    label = "Take Loan",
                    gradient = listOf(Sky500, Sky600),
                    onClick = onTakeLoan
                )
            }
        }

        // Daily Log Component
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Recent Activities",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Sample events
                    EventsData.sampleEvents.forEach { event ->
                        EventItem(event = event)
                        if (event != EventsData.sampleEvents.last()) {
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FinancialMetric(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: androidx.compose.ui.graphics.Color,
    iconBackground: androidx.compose.ui.graphics.Color,
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Gray50
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    modifier = Modifier.size(16.dp),
                    tint = iconColor
                )
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Column {
                Text(
                    text = label,
                    fontSize = 10.sp,
                    color = Gray600
                )
                Text(
                    text = value,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = valueColor
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    gradient: List<androidx.compose.ui.graphics.Color>,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = androidx.compose.ui.graphics.Color.Transparent
        ),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(gradient),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 12.dp, horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    modifier = Modifier.size(20.dp),
                    tint = androidx.compose.ui.graphics.Color.White
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = label,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}

@Composable
private fun EventItem(event: DailyEvent) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(androidx.compose.ui.graphics.Color(android.graphics.Color.parseColor(event.bgColor))),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = getEventIcon(event.icon),
                contentDescription = event.title,
                modifier = Modifier.size(16.dp),
                tint = androidx.compose.ui.graphics.Color(android.graphics.Color.parseColor(event.color))
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = event.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Gray900
            )
            Text(
                text = event.description,
                fontSize = 12.sp,
                color = Gray600,
                lineHeight = 16.sp
            )
        }
    }
}

private fun getEventIcon(iconName: String): ImageVector {
    return when (iconName) {
        "attach_money" -> Icons.Default.AttachMoney
        "home" -> Icons.Default.Home
        "trending_up" -> Icons.Default.TrendingUp
        else -> Icons.Default.Info
    }
}