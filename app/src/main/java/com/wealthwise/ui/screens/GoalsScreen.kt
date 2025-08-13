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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wealthwise.data.model.Goal
import com.wealthwise.data.model.GoalCategory
import com.wealthwise.data.model.GoalsData
import com.wealthwise.ui.theme.*
import java.text.NumberFormat
import java.util.*

/**
 * GoalsScreen - Displays and manages user financial goals
 * 
 * Features:
 * - Shows list of active goals with progress bars
 * - Floating Action Button to add new goals
 * - Clickable goal cards for editing/contributing
 * - Visual progress indicators and completion status
 * - Kenyan Shilling currency formatting
 * 
 * UI Structure: Header → Goal Cards List → FAB for adding goals
 */
@Composable
fun GoalsScreen(
    goals: List<Goal> = GoalsData.defaultGoals,
    onAddGoal: () -> Unit,        // Triggered by FAB click
    onGoalClick: (Goal) -> Unit   // Triggered by goal card click
) {
    // Format currency in Kenyan locale
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        // Scrollable list of goals
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp), // Space for FAB
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
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
                        Text(
                            text = "Financial Goals",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Gray900
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Track your progress towards financial freedom",
                            fontSize = 14.sp,
                            color = Gray600,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            
            // Goals List - Dynamic list of user's financial goals
            items(goals) { goal ->
                GoalCard(
                    goal = goal,
                    numberFormat = numberFormat,
                    onClick = { onGoalClick(goal) }  // Open edit dialog when card tapped
                )
            }
        }
        
        // Floating Action Button - Add new goal
        FloatingActionButton(
            onClick = onAddGoal,  // Triggers add goal dialog
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Emerald500
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Goal",
                tint = androidx.compose.ui.graphics.Color.White
            )
        }
    }
}

/**
 * GoalCard - Individual goal display component
 * 
 * Shows:
 * - Goal icon with category-specific colors
 * - Title, description, and completion status
 * - Current amount vs target amount
 * - Visual progress bar with percentage
 * - Clickable to open contribution/edit dialog
 */
@Composable
private fun GoalCard(
    goal: Goal,
    numberFormat: NumberFormat,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,  // Makes entire card clickable
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Goal Icon
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(getGoalIconBackground(goal.category)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = getGoalIcon(goal.icon),
                        contentDescription = goal.title,
                        modifier = Modifier.size(24.dp),
                        tint = getGoalIconColor(goal.category)
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // Goal Info
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = goal.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Gray900
                        )
                        
                        if (goal.isCompleted) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Completed",
                                modifier = Modifier.size(20.dp),
                                tint = Emerald500
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = goal.description,
                        fontSize = 14.sp,
                        color = Gray600,
                        lineHeight = 18.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Progress Section - Shows financial progress toward goal
            Column {
                // Current amount and completion percentage
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ksh ${numberFormat.format(goal.currentAmount)}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Gray900
                    )
                    
                    Text(
                        text = "${(goal.getProgressPercentage() * 100).toInt()}%",  // Progress as percentage
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Emerald600
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Visual Progress Bar - fills based on current/target ratio
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Gray200)  // Background track
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(goal.getProgressPercentage())  // Fill width based on progress
                            .background(
                                if (goal.isCompleted) Emerald500 else Emerald400,  // Different color when complete
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Target amount display
                Text(
                    text = "Target: Ksh ${numberFormat.format(goal.targetAmount)}",
                    fontSize = 12.sp,
                    color = Gray500
                )
            }
        }
    }
}

private fun getGoalIcon(iconName: String): ImageVector {
    return when (iconName) {
        "security" -> Icons.Default.Security
        "computer" -> Icons.Default.Computer
        "trending_up" -> Icons.Default.TrendingUp
        "flight" -> Icons.Default.Flight
        else -> Icons.Default.Star
    }
}

private fun getGoalIconColor(category: GoalCategory): androidx.compose.ui.graphics.Color {
    return when (category) {
        GoalCategory.EMERGENCY_FUND -> Emerald600
        GoalCategory.PURCHASE -> Sky600
        GoalCategory.INVESTMENT -> Purple500
        GoalCategory.TRAVEL -> Orange500
    }
}

private fun getGoalIconBackground(category: GoalCategory): androidx.compose.ui.graphics.Color {
    return when (category) {
        GoalCategory.EMERGENCY_FUND -> Emerald50
        GoalCategory.PURCHASE -> Sky50
        GoalCategory.INVESTMENT -> Gray100
        GoalCategory.TRAVEL -> Gray100
    }
}