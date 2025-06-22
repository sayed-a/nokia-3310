package nostalgia.devices.nokia3310.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.extensions.ButtonState
import nostalgia.devices.nokia3310.extensions.Mode
import nostalgia.devices.nokia3310.extensions.ScaleType
import nostalgia.devices.nokia3310.ui.theme.CancelButtonShape
import nostalgia.devices.nokia3310.ui.theme.DefaultTypography
import nostalgia.devices.nokia3310.ui.theme.KeypadDark
import nostalgia.devices.nokia3310.ui.theme.MenuButtonLight

enum class ArrowDirection { UP, DOWN, NONE }

interface ArrowButtonListener {
    fun onPressed(arrowDirection: ArrowDirection)
}
@Composable
fun ArrowButtons(buttonListener: ButtonListener, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(90.dp)
            .height(40.dp)
            .graphicsLayer { rotationZ = 145f }
            .shadow(6.dp, CancelButtonShape(cornerRadiusValue = 12.dp.value))
            .background(
                Brush.horizontalGradient(listOf(MenuButtonLight, KeypadDark)),
                CancelButtonShape(cornerRadiusValue = 12.dp.value)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.75f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "<",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = DefaultTypography.bodyMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = ">",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = DefaultTypography.bodyMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}