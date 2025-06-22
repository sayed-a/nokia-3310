package nostalgia.devices.nokia3310.extensions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.shell.ArrowDirection

enum class ButtonState { Pressed, Idle }
enum class Mode { Full, Split }
enum class ScaleType { Full, Top, Bottom }
fun Modifier.bounceClick(mode: Mode = Mode.Full, buttonListener: ButtonListener? = null) = composed {
    var scaleType by remember { mutableStateOf(if (mode == Mode.Full) ScaleType.Full else ScaleType.Top) }
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.90f else 1f)
    var width by remember { mutableIntStateOf(0) }

    this
        .pointerInput(scaleType) {
            detectTapGestures { offset ->
                if (mode == Mode.Split) {
                    val halfWidth = width / 2
                    scaleType = when {
                        offset.x < halfWidth -> ScaleType.Top
                        offset.x >= halfWidth -> ScaleType.Bottom
                        else -> ScaleType.Top
                    }
                }
            }
        }
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    if (mode == Mode.Split) {
                        val offset = currentEvent.changes[0].position
                        val halfWidth = width / 2
                        var arrowDirection = ArrowDirection.NONE

                        when {
                            offset.x < halfWidth -> {
                                scaleType = ScaleType.Bottom
                                arrowDirection = ArrowDirection.DOWN
                            }
                            offset.x >= halfWidth -> {
                                scaleType = ScaleType.Top
                                arrowDirection = ArrowDirection.UP
                            }
                            else -> {}
                        }

                        buttonListener?.onArrowPressed(arrowDirection)
                    }

                    ButtonState.Pressed
                }
            }
        }
        .graphicsLayer {
            transformOrigin = when (scaleType) {
                ScaleType.Full -> TransformOrigin.Center
                ScaleType.Top -> TransformOrigin(0f, 0.5f)
                ScaleType.Bottom -> TransformOrigin(1f, 0.5f)
            }

            scaleX = scale
            scaleY = if (mode == Mode.Full) scale else 1f
        }
        .onSizeChanged {
            width = it.width
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
}