package nostalgia.devices.nokia3310.apps.menu

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MenuApp(menuViewModel: MenuViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val menuItem = menuViewModel.currentMenuItem.collectAsStateWithLifecycle()

    Row(modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(top = 25.dp)
        ) {
            Text(
                menuItem.value.text,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp)
            )
            Spacer(Modifier.height(10.dp))
            Image(
                painterResource(menuItem.value.pictogramResId),
                "Menu Pictogram",
                modifier = Modifier.fillMaxWidth(0.7f).fillMaxHeight(0.7f)
            )
            Text("Select")
        }

        Box(Modifier.fillMaxHeight().fillMaxWidth().background(Black))
    }
}