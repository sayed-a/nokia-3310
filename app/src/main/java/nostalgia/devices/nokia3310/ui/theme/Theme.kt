package nostalgia.devices.nokia3310.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black

private val ColorScheme = lightColorScheme(primary = Black)
val DefaultTypography = Typography()

@Composable
fun Nokia3310Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography(
            displayLarge = DefaultTypography.displayLarge.copy(fontFamily = ScreenFont),
            displayMedium = DefaultTypography.displayMedium.copy(fontFamily = ScreenFont),
            displaySmall = DefaultTypography.displaySmall.copy(fontFamily = ScreenFont),

            headlineLarge = DefaultTypography.headlineLarge.copy(fontFamily = ScreenFont),
            headlineMedium = DefaultTypography.headlineMedium.copy(fontFamily = ScreenFont),
            headlineSmall = DefaultTypography.headlineSmall.copy(fontFamily = ScreenFont),

            titleLarge = DefaultTypography.titleLarge.copy(fontFamily = ScreenFont),
            titleMedium = DefaultTypography.titleMedium.copy(fontFamily = ScreenFont),
            titleSmall = DefaultTypography.titleSmall.copy(fontFamily = ScreenFont),

            bodyLarge = DefaultTypography.bodyLarge.copy(fontFamily = ScreenFont),
            bodyMedium = DefaultTypography.bodyMedium.copy(fontFamily = ScreenFont),
            bodySmall = DefaultTypography.bodySmall.copy(fontFamily = ScreenFont),

            labelLarge = DefaultTypography.labelLarge.copy(fontFamily = ScreenFont),
            labelMedium = DefaultTypography.labelMedium.copy(fontFamily = ScreenFont),
            labelSmall = DefaultTypography.labelSmall.copy(fontFamily = ScreenFont)
        ),
        content = content
    )
}