package nostalgia.devices.nokia3310.apps.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nostalgia.devices.nokia3310.apps.common.BatteryBar
import nostalgia.devices.nokia3310.apps.common.SignalBar
import nostalgia.devices.nokia3310.apps.common.Time

@Composable
fun HomeApp(modifier: Modifier = Modifier) {
    Column {
        Row(modifier.fillMaxWidth().height(20.dp), horizontalArrangement = Arrangement.End) { Time() }
        Row(modifier.fillMaxWidth().height(125.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            SignalBar()
            NetworkOperator()
            BatteryBar()
        }
        Row(modifier.fillMaxWidth().height(20.dp), horizontalArrangement = Arrangement.Center) {
            Text("Menu")
        }
    }
}