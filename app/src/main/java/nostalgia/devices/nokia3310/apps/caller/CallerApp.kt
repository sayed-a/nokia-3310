package nostalgia.devices.nokia3310.apps.caller

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nostalgia.devices.nokia3310.R
import nostalgia.devices.nokia3310.apps.common.BatteryBar
import nostalgia.devices.nokia3310.apps.common.SignalBar
import nostalgia.devices.nokia3310.apps.common.Time
import nostalgia.devices.nokia3310.ui.theme.ScreenColour

@Composable
fun CallerApp(modifier: Modifier = Modifier) {
    Column {
        Row(modifier
            .fillMaxWidth()
            .height(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(painterResource(R.drawable.call), "Call Icon")
            Time()
        }
        Row(modifier
            .fillMaxWidth()
            .height(125.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            SignalBar()
            Row(Modifier.fillMaxWidth(0.8f)) {
                Box(Modifier.size(25.dp).background(Black)) {
                    Image(
                        painterResource(R.drawable.call_up), "Call Up Icon",
                        colorFilter = ColorFilter.tint(ScreenColour),
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(Modifier.width(10.dp))

                Text("Call 1")
            }
            BatteryBar()
        }
        Row(modifier
            .fillMaxWidth()
            .height(20.dp), horizontalArrangement = Arrangement.Center) {
            Text("End")
        }
    }
}