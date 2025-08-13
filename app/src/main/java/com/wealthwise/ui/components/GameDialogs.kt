package com.wealthwise.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.wealthwise.data.model.*
import com.wealthwise.ui.theme.*
import java.text.NumberFormat
import java.util.*

@Composable
fun PayBillsDialog(
    gameState: GameState,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    val hasSufficientFunds = gameState.balance >= gameState.monthlyExpenses
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Icon(
                    imageVector = Icons.Default.Payment,
                    contentDescription = "Pay Bills",
                    modifier = Modifier.size(48.dp),
                    tint = Orange500
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Pay Monthly Bills",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Amount
                Text(
                    text = "Ksh ${numberFormat.format(gameState.monthlyExpenses)}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange500
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Description
                Text(
                    text = "This will deduct your monthly expenses from your balance",
                    fontSize = 14.sp,
                    color = Gray600,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Balance Check
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (hasSufficientFunds) Emerald50 else Red50
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (hasSufficientFunds) Icons.Default.CheckCircle else Icons.Default.Warning,
                            contentDescription = "Balance Status",
                            tint = if (hasSufficientFunds) Emerald600 else Red500
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Column {
                            Text(
                                text = "Current Balance",
                                fontSize = 12.sp,
                                color = Gray600
                            )
                            Text(
                                text = "Ksh ${numberFormat.format(gameState.balance)}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (hasSufficientFunds) Emerald600 else Red500
                            )
                        }
                    }
                }
                
                if (!hasSufficientFunds) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Insufficient funds! You need Ksh ${numberFormat.format(gameState.monthlyExpenses - gameState.balance)} more.",
                        fontSize = 12.sp,
                        color = Red500,
                        textAlign = TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Gray600
                        )
                    ) {
                        Text("Cancel")
                    }
                    
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier.weight(1f),
                        enabled = hasSufficientFunds,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Orange500,
                            disabledContainerColor = Gray300
                        )
                    ) {
                        Text("Pay Now")
                    }
                }
            }
        }
    }
}

@Composable
fun InvestmentDialog(
    gameState: GameState,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var investmentAmount by remember { mutableStateOf("") }
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    val amount = investmentAmount.toIntOrNull() ?: 0
    val hasSufficientBalance = amount > 0 && amount <= gameState.balance
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = "Invest",
                    modifier = Modifier.size(48.dp),
                    tint = Emerald500
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Make Investment",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Current Balance
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Emerald50
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Available Balance",
                            fontSize = 12.sp,
                            color = Gray600
                        )
                        Text(
                            text = "Ksh ${numberFormat.format(gameState.balance)}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Emerald600
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Amount Input
                OutlinedTextField(
                    value = investmentAmount,
                    onValueChange = { investmentAmount = it.filter { char -> char.isDigit() } },
                    label = { Text("Investment Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = investmentAmount.isNotEmpty() && !hasSufficientBalance,
                    supportingText = {
                        if (investmentAmount.isNotEmpty() && !hasSufficientBalance) {
                            Text(
                                text = if (amount <= 0) "Please enter a valid amount" else "Insufficient balance",
                                color = Red500
                            )
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Expected Return
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Sky50
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = Sky600
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Text(
                            text = "Estimated 8% annual return",
                            fontSize = 14.sp,
                            color = Sky700
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Gray600
                        )
                    ) {
                        Text("Cancel")
                    }
                    
                    Button(
                        onClick = { onConfirm(amount) },
                        modifier = Modifier.weight(1f),
                        enabled = hasSufficientBalance,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Emerald500,
                            disabledContainerColor = Gray300
                        )
                    ) {
                        Text("Invest")
                    }
                }
            }
        }
    }
}

@Composable
fun LoanDialog(
    gameState: GameState,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var loanAmount by remember { mutableStateOf("") }
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    val amount = loanAmount.toIntOrNull() ?: 0
    val isValidAmount = amount > 0
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Icon(
                    imageVector = Icons.Default.AccountBalance,
                    contentDescription = "Take Loan",
                    modifier = Modifier.size(48.dp),
                    tint = Sky500
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Take Loan",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Amount Input
                OutlinedTextField(
                    value = loanAmount,
                    onValueChange = { loanAmount = it.filter { char -> char.isDigit() } },
                    label = { Text("Loan Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = loanAmount.isNotEmpty() && !isValidAmount,
                    supportingText = {
                        if (loanAmount.isNotEmpty() && !isValidAmount) {
                            Text(
                                text = "Please enter a valid amount",
                                color = Red500
                            )
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Loan Terms
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Orange50
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Percent,
                                contentDescription = "Interest Rate",
                                tint = Orange600
                            )
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            Text(
                                text = "Interest rate: 12% per year",
                                fontSize = 14.sp,
                                color = Orange700
                            )
                        }
                    }
                    
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Sky50
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Repayment Terms",
                                tint = Sky600
                            )
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            Text(
                                text = "Repay within 12 months",
                                fontSize = 14.sp,
                                color = Sky700
                            )
                        }
                    }
                }
                
                if (amount > 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Gray50
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Loan Summary",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Gray900
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Amount: Ksh ${numberFormat.format(amount)}",
                                fontSize = 12.sp,
                                color = Gray600
                            )
                            Text(
                                text = "Total to repay: Ksh ${numberFormat.format((amount * 1.12).toInt())}",
                                fontSize = 12.sp,
                                color = Gray600
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Gray600
                        )
                    ) {
                        Text("Cancel")
                    }
                    
                    Button(
                        onClick = { onConfirm(amount) },
                        modifier = Modifier.weight(1f),
                        enabled = isValidAmount,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Sky500,
                            disabledContainerColor = Gray300
                        )
                    ) {
                        Text("Take Loan")
                    }
                }
            }
        }
    }
}

/**
 * AddGoalDialog - Modal dialog for creating new financial goals
 * 
 * Features:
 * - Text inputs for title, description, and target amount
 * - Category selection with FilterChips (Emergency Fund, Purchase, Investment, Travel)
 * - Form validation ensuring all fields are filled
 * - Auto-selects appropriate icon based on category
 * - Returns all data to ViewModel for goal creation
 */
@Composable
fun AddGoalDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Int, GoalCategory, String) -> Unit
) {
    // Form state management
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var targetAmount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(GoalCategory.PURCHASE) }
    var selectedIcon by remember { mutableStateOf("star") }
    
    // Form validation
    val amount = targetAmount.toIntOrNull() ?: 0
    val isValidForm = title.isNotEmpty() && description.isNotEmpty() && amount > 0
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Add Goal",
                    modifier = Modifier.size(48.dp),
                    tint = Emerald500
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Add New Goal",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Goal Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = targetAmount,
                    onValueChange = { targetAmount = it.filter { char -> char.isDigit() } },
                    label = { Text("Target Amount (Ksh)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = targetAmount.isNotEmpty() && amount <= 0,
                    supportingText = {
                        if (targetAmount.isNotEmpty() && amount <= 0) {
                            Text(
                                text = "Please enter a valid amount",
                                color = Red500
                            )
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Category",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Gray700,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GoalCategory.values().forEach { category ->
                        FilterChip(
                            onClick = { 
                                selectedCategory = category
                                selectedIcon = when(category) {
                                    GoalCategory.EMERGENCY_FUND -> "security"
                                    GoalCategory.PURCHASE -> "computer"
                                    GoalCategory.INVESTMENT -> "trending_up"
                                    GoalCategory.TRAVEL -> "flight"
                                }
                            },
                            label = { 
                                Text(
                                    text = category.name.replace('_', ' ').lowercase().replaceFirstChar { it.uppercase() },
                                    fontSize = 12.sp
                                ) 
                            },
                            selected = selectedCategory == category,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Gray600
                        )
                    ) {
                        Text("Cancel")
                    }
                    
                    Button(
                        onClick = { onConfirm(title, description, amount, selectedCategory, selectedIcon) },
                        modifier = Modifier.weight(1f),
                        enabled = isValidForm,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Emerald500,
                            disabledContainerColor = Gray300
                        )
                    ) {
                        Text("Add Goal")
                    }
                }
            }
        }
    }
}

/**
 * EditGoalDialog - Modal dialog for interacting with existing goals
 * 
 * Features:
 * - Displays goal details, progress, and completion status
 * - Shows current vs target amounts with visual progress bar
 * - Contribution input with balance validation
 * - Different UI states for completed vs active goals
 * - Options to contribute money or remove goal entirely
 * - Real-time balance checking to prevent overspending
 */
@Composable
fun EditGoalDialog(
    goal: Goal,
    gameState: GameState,
    onDismiss: () -> Unit,
    onContribute: (Int) -> Unit,  // Add money to goal
    onRemove: () -> Unit          // Delete goal entirely
) {
    // Contribution form state
    var contributionAmount by remember { mutableStateOf("") }
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    val amount = contributionAmount.toIntOrNull() ?: 0
    val hasSufficientBalance = amount > 0 && amount <= gameState.balance
    val remainingAmount = goal.targetAmount - goal.currentAmount
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = when(goal.icon) {
                        "security" -> Icons.Default.Security
                        "computer" -> Icons.Default.Computer
                        "trending_up" -> Icons.Default.TrendingUp
                        "flight" -> Icons.Default.Flight
                        else -> Icons.Default.Star
                    },
                    contentDescription = goal.title,
                    modifier = Modifier.size(48.dp),
                    tint = when(goal.category) {
                        GoalCategory.EMERGENCY_FUND -> Emerald600
                        GoalCategory.PURCHASE -> Sky600
                        GoalCategory.INVESTMENT -> Purple500
                        GoalCategory.TRAVEL -> Orange500
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = goal.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray900,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = goal.description,
                    fontSize = 14.sp,
                    color = Gray600,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (goal.isCompleted) Emerald50 else Sky50
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Current",
                                fontSize = 12.sp,
                                color = Gray600
                            )
                            Text(
                                text = "Target",
                                fontSize = 12.sp,
                                color = Gray600
                            )
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Ksh ${numberFormat.format(goal.currentAmount)}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (goal.isCompleted) Emerald600 else Sky600
                            )
                            Text(
                                text = "Ksh ${numberFormat.format(goal.targetAmount)}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Gray900
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        LinearProgressIndicator(
                            progress = goal.getProgressPercentage(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = if (goal.isCompleted) Emerald500 else Sky500
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "${(goal.getProgressPercentage() * 100).toInt()}% Complete",
                            fontSize = 12.sp,
                            color = if (goal.isCompleted) Emerald600 else Sky600,
                            fontWeight = FontWeight.Medium
                        )
                        
                        if (!goal.isCompleted && remainingAmount > 0) {
                            Text(
                                text = "Ksh ${numberFormat.format(remainingAmount)} remaining",
                                fontSize = 12.sp,
                                color = Gray600
                            )
                        }
                    }
                }
                
                if (!goal.isCompleted) {
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Emerald50
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBalanceWallet,
                                contentDescription = "Available Balance",
                                tint = Emerald600
                            )
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            Column {
                                Text(
                                    text = "Available Balance",
                                    fontSize = 12.sp,
                                    color = Gray600
                                )
                                Text(
                                    text = "Ksh ${numberFormat.format(gameState.balance)}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Emerald600
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = contributionAmount,
                        onValueChange = { contributionAmount = it.filter { char -> char.isDigit() } },
                        label = { Text("Contribution Amount (Ksh)") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = contributionAmount.isNotEmpty() && !hasSufficientBalance,
                        supportingText = {
                            if (contributionAmount.isNotEmpty() && !hasSufficientBalance) {
                                Text(
                                    text = if (amount <= 0) "Please enter a valid amount" else "Insufficient balance",
                                    color = Red500
                                )
                            }
                        }
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                if (goal.isCompleted) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onRemove,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Red500
                            )
                        ) {
                            Text("Remove")
                        }
                        
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Emerald500
                            )
                        ) {
                            Text("Done")
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Gray600
                            )
                        ) {
                            Text("Cancel")
                        }
                        
                        if (contributionAmount.isNotEmpty()) {
                            Button(
                                onClick = { onContribute(amount) },
                                modifier = Modifier.weight(1f),
                                enabled = hasSufficientBalance,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Emerald500,
                                    disabledContainerColor = Gray300
                                )
                            ) {
                                Text("Contribute")
                            }
                        } else {
                            OutlinedButton(
                                onClick = onRemove,
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Red500
                                )
                            ) {
                                Text("Remove")
                            }
                        }
                    }
                }
            }
        }
    }
}