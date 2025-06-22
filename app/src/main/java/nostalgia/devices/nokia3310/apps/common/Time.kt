package nostalgia.devices.nokia3310.apps.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun Time() {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    var currentTime by remember { mutableStateOf(LocalDateTime.now().toLocalTime().format(formatter)) }

    LaunchedEffect(LocalContext) {
        while (true) {
            currentTime = LocalDateTime.now().toLocalTime().format(formatter)
            delay(1000)
        }
    }

    Text(currentTime)
}