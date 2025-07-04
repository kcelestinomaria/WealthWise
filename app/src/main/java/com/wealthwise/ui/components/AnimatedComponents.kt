package com.wealthwise.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedWealthCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    delayMillis: Int = 0,
    content: @Composable ColumnScope.() -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        visible = true
    }
    
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(300, easing = EaseOutCubic)
        ) + fadeIn(animationSpec = tween(300))
    ) {
        WealthCard(
            modifier = modifier,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            content = content
        )
    }
}

@Composable
fun AnimatedGameButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    emoji: String? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = if (emoji != null) "$emoji $text" else text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun AnimatedDecisionButton(
    option: com.wealthwise.data.model.DecisionOption,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    delayMillis: Int = 0
) {
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        visible = true
    }
    
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(400, easing = EaseOutCubic)
        ) + fadeIn(animationSpec = tween(400))
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                width = 2.dp,
                brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary)
            )
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "${option.emoji} ${option.label}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (option.cost > 0 || option.gain > 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildString {
                            if (option.cost > 0) append("Cost: ${formatCurrency(option.cost)}")
                            if (option.cost > 0 && option.gain > 0) append(" • ")
                            if (option.gain > 0) append("Gain: ${formatCurrency(option.gain)}")
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedMoneyDisplay(
    amount: Double,
    label: String,
    emoji: String = "💰",
    color: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,
    animateValue: Boolean = true
) {
    var targetValue by remember { mutableStateOf(if (animateValue) 0.0 else amount) }
    
    LaunchedEffect(amount) {
        if (animateValue) {
            targetValue = amount
        }
    }
    
    val animatedValue by animateDoubleAsState(
        targetValue = targetValue,
        animationSpec = tween(1000, easing = EaseOutCubic),
        label = "money_animation"
    )
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$emoji $label",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = formatCurrency(if (animateValue) animatedValue else amount),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
fun PulsingIcon(
    emoji: String,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    Text(
        text = emoji,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier.scale(scale)
    )
}

@Composable
fun SlideInText(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyLarge,
    delayMillis: Int = 0
) {
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(delayMillis.toLong())
        visible = true
    }
    
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it / 4 },
            animationSpec = tween(300, easing = EaseOutCubic)
        ) + fadeIn(animationSpec = tween(300))
    ) {
        Text(
            text = text,
            style = style,
            modifier = modifier
        )
    }
}

@Composable
fun CountUpText(
    targetValue: Double,
    prefix: String = "",
    suffix: String = "",
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.titleLarge,
    color: Color = MaterialTheme.colorScheme.onSurface,
    durationMillis: Int = 1000
) {
    var currentValue by remember { mutableStateOf(0.0) }
    
    LaunchedEffect(targetValue) {
        currentValue = targetValue
    }
    
    val animatedValue by animateDoubleAsState(
        targetValue = currentValue,
        animationSpec = tween(durationMillis, easing = EaseOutCubic),
        label = "count_up"
    )
    
    Text(
        text = "$prefix${formatCurrency(animatedValue)}$suffix",
        style = style,
        color = color
    )
}

// Extension function for bounce click animation
fun Modifier.bounceClick() = composed {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(100),
        label = "bounce"
    )
    
    this.scale(scale)
}

// Utility function for double animation
@Composable
fun animateDoubleAsState(
    targetValue: Double,
    animationSpec: AnimationSpec<Float> = spring(),
    label: String = "DoubleAnimation"
): State<Double> {
    val animatedFloat by animateFloatAsState(
        targetValue = targetValue.toFloat(),
        animationSpec = animationSpec,
        label = label
    )
    return derivedStateOf { animatedFloat.toDouble() }
} 