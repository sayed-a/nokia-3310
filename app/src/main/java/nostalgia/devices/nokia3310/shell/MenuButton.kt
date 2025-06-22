package nostalgia.devices.nokia3310.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.ui.theme.Blue
import nostalgia.devices.nokia3310.ui.theme.MenuButtonShape
import nostalgia.devices.nokia3310.ui.theme.KeypadDark
import nostalgia.devices.nokia3310.ui.theme.MenuButtonLight

@Composable
fun MenuButton(buttonListener: ButtonListener, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(120.dp)
            .height(40.dp)
            .background(
                Brush.horizontalGradient(listOf(MenuButtonLight, KeypadDark)),
                MenuButtonShape
            )
            .shadow(6.dp, MenuButtonShape)
            .clickable { buttonListener.onMenuPressed() },
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .width(90.dp)
                .height(3.dp)
                .background(Blue, MenuButtonShape)
        )
    }
}