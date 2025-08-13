package com.wealthwise.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.wealthwise.data.model.Career
import com.wealthwise.data.model.CareersData
import com.wealthwise.ui.theme.*
import java.text.NumberFormat
import java.util.*

@Composable
fun CareerSelectionScreen(
    onCareerSelected: (Career) -> Unit
) {
    var selectedCareer by remember { mutableStateOf<Career?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
            .padding(24.dp)
    ) {
        // Header
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose Your Career Path",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Gray900,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Select the career that matches your goals",
                fontSize = 16.sp,
                color = Gray600,
                textAlign = TextAlign.Center
            )
        }

        // Career Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(CareersData.careers) { career ->
                CareerCard(
                    career = career,
                    isSelected = selectedCareer?.id == career.id,
                    onSelect = { selectedCareer = career }
                )
            }
        }

        // Select Button
        if (selectedCareer != null) {
            Button(
                onClick = { selectedCareer?.let(onCareerSelected) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Emerald500
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Select",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Choose This Career",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun CareerCard(
    career: Career,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val icon = getCareerIcon(career.icon)
    val numberFormat = NumberFormat.getNumberInstance(Locale("en", "KE"))
    
    Card(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Emerald100 else androidx.compose.ui.graphics.Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 6.dp else 2.dp
        ),
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(2.dp, Emerald500)
        } else null
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Emerald50),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = career.title,
                    modifier = Modifier.size(24.dp),
                    tint = Emerald600
                )
            }
            
            // Career Info
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = career.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray900,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Ksh ${numberFormat.format(career.salary)}/month",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Emerald600
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = career.description,
                    fontSize = 10.sp,
                    color = Gray600,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }
    }
}

private fun getCareerIcon(iconName: String): ImageVector {
    return when (iconName) {
        "school" -> Icons.Default.School
        "engineering" -> Icons.Default.Engineering
        "local_hospital" -> Icons.Default.LocalHospital
        "account_balance" -> Icons.Default.AccountBalance
        "trending_up" -> Icons.Default.TrendingUp
        "computer" -> Icons.Default.Computer
        else -> Icons.Default.Work
    }
}