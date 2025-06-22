package nostalgia.devices.nokia3310.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import nostalgia.devices.nokia3310.ui.theme.ScreenColour
import nostalgia.devices.nokia3310.ui.theme.ScreenColourOff

@Composable
fun Screen(content: @Composable () -> Unit, isBacklit: Boolean, modifier: Modifier = Modifier) {
    val backgroundColour = if (isBacklit) ScreenColour else ScreenColourOff

    Box(
        modifier = modifier
            .background(backgroundColour, RoundedCornerShape(5))
            .fillMaxWidth(0.6f)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5),
                spotColor = Black.copy(alpha = 0.5f),
                ambientColor = Black.copy(alpha = 0.0f)
            )
            .clip(RoundedCornerShape(5))
            .height(180.dp)
            .padding(5.dp)
    ) {
        content()
    }
}