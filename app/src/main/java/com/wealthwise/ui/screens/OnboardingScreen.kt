package com.wealthwise.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wealthwise.ui.theme.*

data class FeatureCard(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val iconColor: androidx.compose.ui.graphics.Color,
    val backgroundColor: androidx.compose.ui.graphics.Color
)

@Composable
fun OnboardingScreen(
    onContinue: () -> Unit
) {
    val features = listOf(
        FeatureCard(
            icon = Icons.Default.Business,
            title = "Build Wealth",
            description = "Learn to grow your money through smart investments and savings strategies",
            iconColor = Emerald600,
            backgroundColor = Emerald50
        ),
        FeatureCard(
            icon = Icons.Default.MonetizationOn,
            title = "Make Money",
            description = "Choose your career path and maximize your earning potential",
            iconColor = Sky600,
            backgroundColor = Sky50
        ),
        FeatureCard(
            icon = Icons.Default.TrendingUp,
            title = "Track Progress",
            description = "Monitor your financial journey with detailed insights and achievements",
            iconColor = Purple500,
            backgroundColor = Gray50
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
            .padding(24.dp)
    ) {
        // Header
        Column(
            modifier = Modifier.padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to WealthWise",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Gray900,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Your journey to financial mastery starts here",
                fontSize = 16.sp,
                color = Gray600,
                textAlign = TextAlign.Center
            )
        }

        // Feature Cards
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(features) { feature ->
                FeatureCardItem(feature = feature)
            }
        }

        // Continue Button
        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Emerald500
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Continue",
                modifier = Modifier.padding(vertical = 4.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun FeatureCardItem(feature: FeatureCard) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Container
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(feature.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = feature.icon,
                    contentDescription = feature.title,
                    modifier = Modifier.size(28.dp),
                    tint = feature.iconColor
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Text Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = feature.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = feature.description,
                    fontSize = 14.sp,
                    color = Gray600,
                    lineHeight = 20.sp
                )
            }
        }
    }
}