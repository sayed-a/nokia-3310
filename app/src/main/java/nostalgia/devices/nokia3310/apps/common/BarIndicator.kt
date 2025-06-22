package nostalgia.devices.nokia3310.apps.common

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BarIndicator(level: Int, @DrawableRes iconId: Int, ltr: Boolean, flashLastBar: Boolean = false, modifier: Modifier = Modifier) {
    val horizontalAlignment = if (ltr) Alignment.End else Alignment.Start

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Black,
        targetValue = Transparent,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "flashLastBar"
    )

    Column(
        modifier = modifier
            .width(10.dp)
            .height(125.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = horizontalAlignment
    ) {
        if (level > 0) {
            for (i in level downTo 1) {
                val width = when (i) {
                    4 -> 1f
                    2, 3 -> 0.75f
                    else -> 0.5f
                }

                val background = if (i == level && flashLastBar) color else Black

                Box(modifier.height(20.dp).fillMaxWidth(width).background(background))
                Spacer(Modifier.height(5.dp))
            }
        }

        Image(painterResource(iconId), "Icon")
    }
}