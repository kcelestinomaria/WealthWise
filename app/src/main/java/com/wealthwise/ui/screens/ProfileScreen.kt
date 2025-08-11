package com.wealthwise.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wealthwise.data.model.GameState
import com.wealthwise.ui.theme.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val isUnlocked: Boolean = false
)

data class StatCard(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val color: androidx.compose.ui.graphics.Color
)

data class SettingsItem(
    val title: String,
    val icon: ImageVector,
    val hasToggle: Boolean = false,
    val isToggleEnabled: Boolean = false,
    val onClick: () -> Unit
)

@Composable
fun ProfileScreen(
    gameState: GameState,
    userName: String = "Financial Explorer",
    onLogout: () -> Unit,
    onNotificationsToggle: (Boolean) -> Unit,
    onPrivacyClick: () -> Unit,
    onHelpClick: () -> Unit,
    onAboutClick: () -> Unit,
    onShareProgress: () -> Unit
) {
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    val dateFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
    val joinDate = dateFormat.format(Date())
    
    val achievements = listOf(
        Achievement(
            id = "first_investment",
            title = "First Investment",
            description = "Made your first investment",
            icon = Icons.Default.Star,
            isUnlocked = gameState.savings > 0
        ),
        Achievement(
            id = "goal_achiever",
            title = "Goal Achiever",
            description = "Completed your first goal",
            icon = Icons.Default.Star,
            isUnlocked = false
        ),
        Achievement(
            id = "saver",
            title = "Saver",
            description = "Saved Ksh 10,000",
            icon = Icons.Default.Star,
            isUnlocked = gameState.savings >= 10000
        )
    )
    
    val stats = listOf(
        StatCard(
            title = "Days Played",
            value = gameState.currentDay.toString(),
            icon = Icons.Default.History,
            color = Emerald500
        ),
        StatCard(
            title = "Goals Completed",
            value = "0", // TODO: Implement goal tracking
            icon = Icons.Default.Star,
            color = Sky500
        ),
        StatCard(
            title = "Total Saved",
            value = "Ksh ${numberFormat.format(gameState.savings)}",
            icon = Icons.Default.TrendingUp,
            color = Purple500
        )
    )
    
    val settingsItems = listOf(
        SettingsItem(
            title = "Notifications",
            icon = Icons.Default.Notifications,
            hasToggle = true,
            isToggleEnabled = true,
            onClick = { onNotificationsToggle(!true) }
        ),
        SettingsItem(
            title = "Privacy & Security",
            icon = Icons.Default.Lock,
            onClick = onPrivacyClick
        ),
        SettingsItem(
            title = "Help & Support",
            icon = Icons.Default.Help,
            onClick = onHelpClick
        ),
        SettingsItem(
            title = "About WealthWise",
            icon = Icons.Default.Info,
            onClick = onAboutClick
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Profile Header
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile Avatar
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Emerald100),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(40.dp),
                            tint = Emerald600
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // User Info
                    Text(
                        text = userName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    
                    Text(
                        text = gameState.career,
                        fontSize = 14.sp,
                        color = Gray600
                    )
                    
                    Text(
                        text = "Member since $joinDate",
                        fontSize = 12.sp,
                        color = Gray500
                    )
                }
            }
        }

        // Achievements Section
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
                        text = "Achievements",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(achievements) { achievement ->
                            AchievementBadge(achievement = achievement)
                        }
                    }
                }
            }
        }

        // Stats Cards
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Statistics",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Gray900
                        )
                        
                        IconButton(onClick = onShareProgress) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share Progress",
                                tint = Gray600
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        stats.forEach { stat ->
                            StatCardItem(stat = stat)
                        }
                    }
                }
            }
        }

        // Settings Section
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
                        text = "Settings",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Column {
                        settingsItems.forEach { item ->
                            SettingsItemRow(item = item)
                            if (item != settingsItems.last()) {
                                Divider(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    color = Gray200
                                )
                            }
                        }
                    }
                }
            }
        }

        // Logout Button
        item {
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red500
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Logout",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Logout",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun AchievementBadge(achievement: Achievement) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    if (achievement.isUnlocked) Emerald100 else Gray200
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = achievement.icon,
                contentDescription = achievement.title,
                modifier = Modifier.size(24.dp),
                tint = if (achievement.isUnlocked) Emerald600 else Gray400
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = achievement.title,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = if (achievement.isUnlocked) Gray900 else Gray500
        )
    }
}

@Composable
private fun StatCardItem(stat: StatCard) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(stat.color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = stat.icon,
                contentDescription = stat.title,
                modifier = Modifier.size(20.dp),
                tint = stat.color
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stat.title,
                fontSize = 14.sp,
                color = Gray600
            )
            Text(
                text = stat.value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Gray900
            )
        }
    }
}

@Composable
private fun SettingsItemRow(item: SettingsItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            modifier = Modifier.size(24.dp),
            tint = Gray600
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Gray900,
            modifier = Modifier.weight(1f)
        )
        
        if (item.hasToggle) {
            Switch(
                checked = item.isToggleEnabled,
                onCheckedChange = { item.onClick() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = androidx.compose.ui.graphics.Color.White,
                    checkedTrackColor = Emerald500,
                    uncheckedThumbColor = androidx.compose.ui.graphics.Color.White,
                    uncheckedTrackColor = Gray300
                )
            )
        } else {
            IconButton(onClick = item.onClick) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Go to ${item.title}",
                    tint = Gray400
                )
            }
        }
    }
}