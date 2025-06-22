package nostalgia.devices.nokia3310.shell

import android.media.MediaPlayer
import android.view.HapticFeedbackConstants
import android.view.View.NO_ID
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.R
import nostalgia.devices.nokia3310.ui.theme.DefaultTypography
import nostalgia.devices.nokia3310.ui.theme.KeypadButtonShape
import nostalgia.devices.nokia3310.ui.theme.KeypadDark
import nostalgia.devices.nokia3310.ui.theme.KeypadLight


//region Types
enum class KeypadColumn(val topLeft: Dp, val topRight: Dp, val bottomLeft: Dp, val bottomRight: Dp) {
    LEFT(20.dp, 50.dp, 50.dp, 20.dp),
    CENTER(50.dp, 50.dp, 70.dp, 70.dp),
    RIGHT(50.dp, 20.dp, 20.dp, 50.dp)
}

enum class Key(
    val topText: String,
    val bottomText: String = "",
    val column: KeypadColumn,
    @DrawableRes val bottomIcon: Int = NO_ID
) {
    ONE(topText = "1", bottomIcon = R.drawable.voicemail, column = KeypadColumn.LEFT),
    TWO(topText = "2", bottomText = "ABC", column = KeypadColumn.CENTER),
    THREE(topText = "3", bottomText = "DEF", column = KeypadColumn.RIGHT),
    FOUR(topText = "4", bottomText = "GHI", column = KeypadColumn.LEFT),
    FIVE(topText = "5", bottomText = "JKL", column = KeypadColumn.CENTER),
    SIX(topText = "6", bottomText = "MNO", column = KeypadColumn.RIGHT),
    SEVEN(topText = "7", bottomText = "PQRS", column = KeypadColumn.LEFT),
    EIGHT(topText = "8", bottomText = "TUV", column = KeypadColumn.CENTER),
    NINE(topText = "9", bottomText = "WXYZ", column = KeypadColumn.RIGHT),
    STAR(topText = "*", bottomText = "+", column = KeypadColumn.LEFT),
    ZERO(topText = "0", bottomIcon = R.drawable.space_bar, column = KeypadColumn.CENTER),
    HASH(topText = "#", bottomIcon = R.drawable.shift_lock, column = KeypadColumn.RIGHT)
}
//endregion


//region Composables
@Composable
fun KeyPadButton(
    key: Key,
    buttonListener: ButtonListener,
    modifier: Modifier = Modifier
) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.keypad_beep)
    val view = LocalView.current

    Box(
        modifier = modifier
            .width(70.dp)
            .height(45.dp)
            .background(
                Brush.horizontalGradient(listOf(KeypadLight, KeypadDark)),
                KeypadButtonShape(key.column)
            )
            .shadow(6.dp, KeypadButtonShape(key.column))
            .clickable {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                mediaPlayer.start()
                buttonListener.onKeypadPressed(key)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = key.topText,
                fontSize = 16.sp,
                style = DefaultTypography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            if (key.bottomText.isNotEmpty()) {
                Text(
                    text = key.bottomText,
                    fontSize = 10.sp,
                    style = DefaultTypography.bodyMedium,
                    color = Color.Black
                )
            } else if (key.bottomIcon != NO_ID) {
                Image(painterResource(key.bottomIcon), "Icon")
            }
        }
    }
}
//endregion