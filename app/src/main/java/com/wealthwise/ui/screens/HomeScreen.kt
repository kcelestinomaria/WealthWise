package com.wealthwise.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wealthwise.data.model.Role
import com.wealthwise.ui.components.*
import com.wealthwise.ui.theme.WealthWiseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onStartGame: (String, Role) -> Unit,
    onNavigateToLeaderboard: () -> Unit,
    onNavigateToLearnCenter: () -> Unit,
    modifier: Modifier = Modifier
) {
    var playerName by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf<Role?>(null) }
    var showNameError by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            // App Title
            Text(
                text = "💰 WealthWise",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            Text(
                text = "Master Your Money, One Day at a Time!",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            // Welcome Card
            WealthCard {
                Text(
                    text = "🎮 Welcome to the Game!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Learn financial literacy through 30 days of real Kenyan scenarios. Choose your role, make smart decisions, and build your wealth!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            // Player Name Input
            WealthCard {
                Text(
                    text = "👤 Enter Your Name",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = playerName,
                    onValueChange = { 
                        playerName = it
                        showNameError = false
                    },
                    label = { Text("Player Name") },
                    placeholder = { Text("e.g., John Mwangi") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = showNameError,
                    supportingText = if (showNameError) {
                        { Text("Please enter your name") }
                    } else null
                )
            }
        }

        item {
            // Role Selection
            Text(
                text = "🎭 Choose Your Role",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        items(Role.values().toList()) { role ->
            RoleCard(
                role = role,
                isSelected = selectedRole == role,
                onClick = { selectedRole = role }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            // Start Game Button
            GameButton(
                text = "Start Your Financial Journey",
                emoji = "🚀",
                onClick = {
                    if (playerName.isBlank()) {
                        showNameError = true
                    } else if (selectedRole != null) {
                        onStartGame(playerName, selectedRole!!)
                    }
                },
                enabled = selectedRole != null && playerName.isNotBlank()
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Quick Access Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onNavigateToLeaderboard,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("🏆 Leaderboard")
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