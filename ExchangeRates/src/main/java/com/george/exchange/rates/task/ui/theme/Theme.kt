package com.george.exchange.rates.task.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFB388EB),         // Lighter purple shade
    primaryVariant = Color(0xFF6A1B9A), // Deeper purple shade
    secondary = Color(0xFF4DB6AC),       // Balanced teal shade
    background = Color(0xFF121212),      // Very dark gray for deep night feel
    surface = Color(0xFF1E1E1E),         // Slightly lighter gray for surface
    onSurface = Color(0xFFB0BEC5)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF9C27B0),         // Vibrant purple for primary
    primaryVariant = Color(0xFF6A1B9A), // Deep purple for emphasis
    secondary = Color(0xFF26A69A),       // Vivid teal shade
    background = Color(0xFFE3F2FD),      // Light, soft blue for background
    surface = Color(0xFFFFFFFF),         // Classic white for surfaces
    onSurface = Color(0xFF212121)
)

@Composable
fun ExchangeRatesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
