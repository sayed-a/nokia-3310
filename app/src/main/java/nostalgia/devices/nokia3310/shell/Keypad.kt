package nostalgia.devices.nokia3310.shell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.extensions.bounceClick

@Composable
fun Keypad(buttonListener: ButtonListener) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        items(12) { item ->
            Box(modifier = Modifier.width(70.dp).height(45.dp)) {
                KeyPadButton(Key.entries[item], buttonListener, Modifier.bounceClick())
            }
        }
    }
}