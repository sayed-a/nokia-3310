package nostalgia.devices.nokia3310.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import nostalgia.devices.nokia3310.ui.theme.EarpieceColour
import nostalgia.devices.nokia3310.ui.theme.HorizontalOvalShape
import nostalgia.devices.nokia3310.ui.theme.NokiaFont

@Composable
fun Earpiece(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        for (i in 1..5) {
            Box(
                modifier = modifier
                    .size(width = 8.dp, height = 3.dp)
                    .background(color = EarpieceColour, shape = HorizontalOvalShape())
            )

            Spacer(Modifier.height(3.dp))
        }

        Spacer(Modifier.height(30.dp))

        Box(
            Modifier
                .background(EarpieceColour, RoundedCornerShape(5.dp))
                .padding(3.dp)
        ) {
            Text("NOKIA", fontFamily = NokiaFont, color = White)
        }
    }
}