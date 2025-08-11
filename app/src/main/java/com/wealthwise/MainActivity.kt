package com.wealthwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.wealthwise.ui.theme.WealthWiseTheme

/**
 * MainActivity - Entry point for the WealthWise financial literacy game
 * 
 * This is the single Activity that hosts the entire Compose UI.
 * Sets up the app theme and launches the main app navigation.
 * 
 * Key responsibilities:
 * - Initialize edge-to-edge display
 * - Apply Material 3 theme
 * - Launch WealthWiseApp composable (main navigation)
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display for modern Android look
        enableEdgeToEdge()
        
        setContent {
            // Apply custom Material 3 theme
            WealthWiseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Launch main app navigation and content
                    WealthWiseApp()
                }
            }
        }
    }
} 