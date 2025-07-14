package com.wealthwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.wealthwise.data.database.WealthWiseDatabase
import com.wealthwise.data.repository.LeaderboardRepository
import com.wealthwise.data.repository.PlayerRepository
import com.wealthwise.ui.screens.*
import com.wealthwise.ui.theme.WealthWiseTheme
import com.wealthwise.viewmodel.GameViewModel
import com.wealthwise.viewmodel.LeaderboardViewModel
import com.wealthwise.viewmodel.GameViewModelFactory
import com.wealthwise.viewmodel.LeaderboardViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Initialize Firebase with error handling
        try {
            FirebaseApp.initializeApp(this)
        } catch (e: Exception) {
            // Firebase initialization failed - app can still work offline
            e.printStackTrace()
        }
        
        setContent {
            WealthWiseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WealthWiseApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WealthWiseApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    
    // Create repositories
    val database = WealthWiseDatabase.getDatabase(androidx.compose.ui.platform.LocalContext.current)
    val firestore = try {
        FirebaseFirestore.getInstance()
    } catch (e: Exception) {
        null // Firebase not available, leaderboard will work offline only
    }
    val playerRepository = PlayerRepository(database.playerDao(), database.transactionDao())
    val leaderboardRepository = LeaderboardRepository(database.leaderboardDao(), firestore)
    
    // Create ViewModels
    val gameViewModel: GameViewModel = viewModel(
        factory = GameViewModelFactory(playerRepository, leaderboardRepository)
    )
    val leaderboardViewModel: LeaderboardViewModel = viewModel(
        factory = LeaderboardViewModelFactory(leaderboardRepository)
    )
    
    // Collect states
    val gameUiState by gameViewModel.uiState.collectAsState()
    val leaderboardUiState by leaderboardViewModel.uiState.collectAsState()
    
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onStartGame = { playerName, role ->
                    gameViewModel.startNewGame(playerName, role)
                    navController.navigate("game") {
                        popUpTo("home") { inclusive = false }
                    }
                },
                onNavigateToLeaderboard = {
                    navController.navigate("leaderboard")
                },
                onNavigateToLearnCenter = {
                    navController.navigate("learn")
                }
            )
        }
        
        composable("game") {
            val player = gameUiState.currentPlayer
            val currentEvent = gameUiState.currentEvent
            
            if (player != null) {
                if (gameUiState.gameCompleted) {
                    EndGameScreen(
                        player = player,
                        onPlayAgain = {
                            gameViewModel.resetGame()
                            navController.navigate("home") {
                                popUpTo("game") { inclusive = true }
                            }
                        },
                        onViewLeaderboard = {
                            navController.navigate("leaderboard")
                        },
                        onBackToHome = {
                            navController.navigate("home") {
                                popUpTo("game") { inclusive = true }
                            }
                        }
                    )
                } else {
                    DailyTurnScreen(
                        player = player,
                        currentEvent = currentEvent,
                        dailyIncome = gameUiState.dailyIncome,
                        lastDecisionResult = gameUiState.lastDecisionResult,
                        onDecisionMade = { option ->
                            gameViewModel.makeDecision(option)
                        },
                        onNavigateToWallet = {
                            navController.navigate("wallet")
                        },
                        onNavigateToLearnCenter = {
                            navController.navigate("learn")
                        },
                        onClearResult = {
                            gameViewModel.clearLastResult()
                        }
                    )
                }
            }
        }
        
        composable("wallet") {
            val player = gameUiState.currentPlayer
            if (player != null) {
                // Collect transactions for the current player
                val transactions by gameViewModel.getTransactionsForPlayer(player.id).collectAsState(initial = emptyList())
                
                WalletScreen(
                    player = player,
                    transactions = transactions,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
        
        composable("learn") {
            LearnCenterScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
        
        composable("leaderboard") {
            LeaderboardScreen(
                leaderboardEntries = leaderboardUiState.topEntries,
                isLoading = leaderboardUiState.isLoading,
                isRefreshing = leaderboardUiState.isRefreshing,
                onBack = {
                    navController.navigateUp()
                },
                onRefresh = {
                    leaderboardViewModel.refreshFromFirebase()
                }
            )
        }
    }
    
    // Handle errors
    gameUiState.errorMessage?.let { error ->
        LaunchedEffect(error) {
            // You could show a snackbar or toast here
            gameViewModel.clearError()
        }
    }
    
    leaderboardUiState.errorMessage?.let { error ->
        LaunchedEffect(error) {
            // You could show a snackbar or toast here
            leaderboardViewModel.clearError()
        }
    }
} 