package nostalgia.devices.nokia3310.shell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.extensions.Mode
import nostalgia.devices.nokia3310.extensions.bounceClick

@Composable
fun ButtonArea(buttonListener: ButtonListener) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(vertical = 10.dp)
    ) {
        Row {
            MenuButton(buttonListener, Modifier.bounceClick())
        }

        Row(Modifier.fillMaxWidth(0.6f), horizontalArrangement = Arrangement.SpaceBetween) {
            CancelButton(buttonListener, Modifier.bounceClick().padding(start = 15.dp))
            ArrowButtons(buttonListener, Modifier.bounceClick(mode = Mode.Split, buttonListener = buttonListener).padding(end = 20.dp))
        }

        Spacer(Modifier.height(60.dp))

        Keypad(buttonListener)
    }
}