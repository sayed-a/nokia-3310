package nostalgia.devices.nokia3310.apps.dialler

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nostalgia.devices.nokia3310.apps.common.BatteryBar
import nostalgia.devices.nokia3310.apps.common.SignalBar
import nostalgia.devices.nokia3310.apps.common.Time

@Composable
fun DiallerApp(viewModel: DiallerViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val number = viewModel.numberFlow.collectAsStateWithLifecycle()
    var availableWidth by remember { mutableFloatStateOf(0f) }

    Column {
        Row(modifier.fillMaxWidth().height(20.dp), horizontalArrangement = Arrangement.End) { Time() }
        Row(modifier.fillMaxWidth().height(125.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            SignalBar()
            Column(
                Modifier
                    .align(Alignment.Bottom)
                    .fillMaxWidth(0.7f)
                    .onSizeChanged { size -> availableWidth = size.width.toFloat() }
            ) {
                DiallerText(
                    number.value,
                    availableWidth = availableWidth,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            BatteryBar()
        }
        Row(modifier.fillMaxWidth().height(20.dp), horizontalArrangement = Arrangement.Center) {
            Text("Call")
        }
    }
}