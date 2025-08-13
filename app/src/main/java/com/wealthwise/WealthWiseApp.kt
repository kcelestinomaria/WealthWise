package com.wealthwise

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wealthwise.data.model.CareersData
import com.wealthwise.ui.components.*
import com.wealthwise.ui.screens.*
import com.wealthwise.ui.screens.auth.SignInScreen
import com.wealthwise.ui.screens.auth.SignUpScreen
import com.wealthwise.viewmodel.WealthWiseViewModel
import com.wealthwise.viewmodel.AuthViewModel

/**
 * Navigation routes for the app screens
 * Each screen has a unique route string for navigation
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")           // App loading screen
    object Welcome : Screen("welcome")         // First-time user welcome
    object SignIn : Screen("sign_in")          // User authentication - sign in
    object SignUp : Screen("sign_up")          // User authentication - sign up
    object Onboarding : Screen("onboarding")   // Tutorial walkthrough
    object CareerSelection : Screen("career_selection") // Choose career path
    object Dashboard : Screen("dashboard")     // Main game dashboard
    object Goals : Screen("goals")             // Financial goals management
    object Profile : Screen("profile")        // User profile and settings
}

/**
 * Bottom navigation bar item configuration
 * Defines the main tabs users can navigate between
 */
data class BottomNavItem(
    val screen: Screen,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

/**
 * WealthWiseApp - Main app navigation and layout structure
 *
 * This composable manages:
 * - Navigation between all app screens
 * - Bottom navigation bar for main tabs
 * - Global app state and dialogs
 * - ViewModel integration for game state
 * - Authentication flow and state management
 *
 * Flow: SignIn → (SignUp optional) → Welcome/Onboarding → Career Selection → Dashboard (with bottom nav)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WealthWiseApp() {
    // Navigation and state management
    val navController = rememberNavController()
    val viewModel: WealthWiseViewModel = viewModel()
    val authViewModel: AuthViewModel = hiltViewModel()

    // Observe UI, game state, and auth state changes
    val uiState by viewModel.uiState.collectAsState()
    val gameState by viewModel.gameState.collectAsState()
    val authUiState by authViewModel.uiState.collectAsState()

    // Determine start destination based on auth state
    val startDestination = when {
        !authUiState.isSignedIn -> Screen.SignIn.route
        authUiState.isAnonymous -> Screen.Welcome.route // Show welcome for anonymous users
        !uiState.isOnboardingCompleted -> Screen.Onboarding.route // Check onboarding for authenticated users
        else -> Screen.Dashboard.route
    }

    // Define bottom navigation tabs (only shown on main screens)
    val bottomNavItems = listOf(
        BottomNavItem(
            screen = Screen.Dashboard,
            title = "Dashboard",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            screen = Screen.Goals,
            title = "Goals",
            selectedIcon = Icons.Filled.Star,
            unselectedIcon = Icons.Outlined.Star
        ),
        BottomNavItem(
            screen = Screen.Profile,
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )

    // Track current screen for bottom bar visibility
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    // Show bottom bar only on main app screens
    val showBottomBar = currentDestination?.route in listOf(
        Screen.Dashboard.route,
        Screen.Goals.route,
        Screen.Profile.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.route == item.screen.route
                        } == true

                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) },
                            selected = isSelected,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Main navigation graph - defines all app screens and their transitions
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                // App loading screen
                composable(Screen.Splash.route) {
                    SplashScreen {
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }

                // First-time user introduction
                composable(Screen.Welcome.route) {
                    WelcomeScreen {
                        navController.navigate(Screen.Onboarding.route)
                    }
                }

                // User authentication - sign in
                composable(Screen.SignIn.route) {
                    val authViewModel: AuthViewModel = hiltViewModel()
                    val authUiState by authViewModel.uiState.collectAsState()

                    // Navigate when authentication succeeds
                    LaunchedEffect(authUiState.isSignedIn) {
                        if (authUiState.isSignedIn) {
                            val destination = if (authUiState.isAnonymous) {
                                Screen.Welcome.route
                            } else if (!uiState.isOnboardingCompleted) {
                                Screen.Onboarding.route
                            } else {
                                Screen.Dashboard.route
                            }
                            navController.navigate(destination) {
                                popUpTo(Screen.SignIn.route) { inclusive = true }
                            }
                        }
                    }

                    SignInScreen(
                        onSignInClick = { email, password ->
                            authViewModel.signInWithEmail(email, password)
                        },
                        onSignUpClick = {
                            navController.navigate(Screen.SignUp.route)
                        },
                        onGoogleSignInClick = {
                            // Handle Google Sign-In (implement launcher)
                        },
                        onAnonymousSignInClick = {
                            authViewModel.signInAnonymously()
                        },
                        onForgotPasswordClick = { email ->
                            authViewModel.resetPassword(email)
                        },
                        isLoading = authUiState.isLoading,
                        errorMessage = authUiState.errorMessage
                    )
                }

                // User authentication - sign up
                composable(Screen.SignUp.route) {
                    val authViewModel: AuthViewModel = hiltViewModel()
                    val authUiState by authViewModel.uiState.collectAsState()

                    // Navigate when sign up succeeds
                    LaunchedEffect(authUiState.isSignedIn) {
                        if (authUiState.isSignedIn && !authUiState.isAnonymous) {
                            val destination = if (!uiState.isOnboardingCompleted) {
                                Screen.Onboarding.route
                            } else {
                                Screen.Dashboard.route
                            }
                            navController.navigate(destination) {
                                popUpTo(Screen.SignUp.route) { inclusive = true }
                            }
                        }
                    }

                    SignUpScreen(
                        onSignUpClick = { email, password, name ->
                            authViewModel.signUpWithEmail(email, password, name)
                        },
                        onSignInClick = {
                            navController.popBackStack()
                        },
                        onBackClick = {
                            navController.popBackStack()
                        },
                        isLoading = authUiState.isLoading,
                        errorMessage = authUiState.errorMessage
                    )
                }

                // Tutorial walkthrough for game mechanics
                composable(Screen.Onboarding.route) {
                    OnboardingScreen {
                        navController.navigate(Screen.CareerSelection.route)
                    }
                }

                // Choose career path (affects starting income/expenses)
                composable(Screen.CareerSelection.route) {
                    CareerSelectionScreen { career ->
                        viewModel.selectCareer(career)  // Initialize game with selected career
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }

                // Main game dashboard - shows financial overview and daily actions
                composable(Screen.Dashboard.route) {
                    DashboardScreen(
                        gameState = gameState,
                        onPayBills = { viewModel.showPayBillsDialog() },
                        onInvest = { viewModel.showInvestmentDialog() },
                        onTakeLoan = { viewModel.showLoanDialog() },
                        onSettingsClick = {
                            navController.navigate(Screen.Profile.route)
                        }
                    )
                }

                // Financial goals management - create, track, and contribute to goals
                composable(Screen.Goals.route) {
                    GoalsScreen(
                        goals = gameState.goals,
                        onAddGoal = {
                            viewModel.showAddGoalDialog()  // Open dialog to create new goal
                        },
                        onGoalClick = { goal ->
                            viewModel.showEditGoalDialog(goal)  // Open dialog to contribute/edit goal
                        }
                    )
                }

                // User profile and settings
                composable(Screen.Profile.route) {
                    ProfileScreen(
                        gameState = gameState,
                        onLogout = {
                            authViewModel.signOut()  // Sign out user
                            viewModel.resetGame()  // Clear all progress
                            navController.navigate(Screen.SignIn.route) {
                                popUpTo(Screen.Dashboard.route) { inclusive = true }
                            }
                        },
                        onNotificationsToggle = { enabled ->
                            // TODO: Implement notifications toggle
                        },
                        onPrivacyClick = {
                            // TODO: Implement privacy screen
                        },
                        onHelpClick = {
                            // TODO: Implement help screen
                        },
                        onAboutClick = {
                            // TODO: Implement about screen
                        },
                        onShareProgress = {
                            // TODO: Implement share functionality
                        }
                    )
                }
            }

            // Global dialogs - shown over current screen when triggered

            // Monthly bills payment dialog - advances to next day when paid
            if (uiState.showPayBillsDialog) {
                PayBillsDialog(
                    gameState = gameState,
                    onDismiss = { viewModel.hidePayBillsDialog() },
                    onConfirm = {
                        viewModel.payBills()  // Deduct expenses, advance day
                    }
                )
            }

            // Investment dialog - allows player to invest money for returns
            if (uiState.showInvestmentDialog) {
                InvestmentDialog(
                    gameState = gameState,
                    onDismiss = { viewModel.hideInvestmentDialog() },
                    onConfirm = { amount ->
                        viewModel.invest(amount)  // Move money to savings/investments
                    }
                )
            }

            // Loan dialog - allows player to borrow money (with interest)
            if (uiState.showLoanDialog) {
                LoanDialog(
                    gameState = gameState,
                    onDismiss = { viewModel.hideLoanDialog() },
                    onConfirm = { amount ->
                        viewModel.takeLoan(amount)  // Add to balance and debt
                    }
                )
            }

            // Create new goal dialog - set target amount and category
            if (uiState.showAddGoalDialog) {
                AddGoalDialog(
                    onDismiss = { viewModel.hideAddGoalDialog() },
                    onConfirm = { title, description, targetAmount, category, icon ->
                        viewModel.addGoal(title, description, targetAmount, category, icon)
                    }
                )
            }

// Edit existing goal dialog - contribute money or remove goal
            uiState.selectedGoal?.let { selectedGoal ->
                if (uiState.showEditGoalDialog) {
                    EditGoalDialog(
                        goal = selectedGoal,
                        gameState = gameState,
                        onDismiss = { viewModel.hideEditGoalDialog() },
                        onContribute = { amount ->
                            viewModel.updateGoal(selectedGoal.id, amount)  // Add money to goal
                        },
                        onRemove = {
                            viewModel.removeGoal(selectedGoal.id)  // Delete goal entirely
                        }
                    )
                }
            }

            // Show success messages
            uiState.successMessage?.let { message ->
                LaunchedEffect(message) {
                    // TODO: Show success message (could use SnackbarHost)
                    viewModel.clearMessages()
                }
            }

            // Show error messages
            uiState.errorMessage?.let { message ->
                LaunchedEffect(message) {
                    // TODO: Show error message (could use SnackbarHost)
                    viewModel.clearMessages()
                }
            }

            // Show auth error messages
            authUiState.errorMessage?.let { message ->
                LaunchedEffect(message) {
                    // TODO: Show auth error message (could use SnackbarHost)
                    authViewModel.clearError()
                }
            }
        }
    }
}