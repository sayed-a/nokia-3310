package nostalgia.devices.nokia3310.apps.dialler

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp

@Composable
fun DiallerText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayLarge.copy(fontSize = 28.sp),
    availableWidth: Float
) {
    val density = LocalDensity.current
    val fontFamilyResolver = LocalFontFamilyResolver.current

    val (topText, bottomText) = remember(text, style, availableWidth, density, fontFamilyResolver) {
        if (text.isEmpty()) {
            "" to ""
        } else {
            var bottomLineText = ""
            var remainingText = text

            var testBottomLine = ""
            var lastFittingSegment = ""
            for (i in text.length - 1 downTo 0) {
                testBottomLine = text[i] + testBottomLine
                val paragraph = Paragraph(
                    text = testBottomLine,
                    style = style,
                    constraints = Constraints(maxWidth = availableWidth.toInt()),
                    density = density,
                    fontFamilyResolver = fontFamilyResolver,
                    maxLines = 1
                )

                if (paragraph.didExceedMaxLines) {
                    break
                }

                lastFittingSegment = testBottomLine
            }

            if (lastFittingSegment.isNotEmpty() && lastFittingSegment.length < text.length) {
                bottomLineText = lastFittingSegment
                remainingText = text.substring(0, text.length - lastFittingSegment.length)
            } else {
                bottomLineText = text
                remainingText = ""
            }

            val topOverflowText = if (remainingText.isNotEmpty()) {
                val topParagraph = Paragraph(
                    text = remainingText,
                    style = style,
                    constraints = Constraints(maxWidth = availableWidth.toInt()),
                    density = density,
                    fontFamilyResolver = fontFamilyResolver,
                    maxLines = 1,
                )

                if (topParagraph.didExceedMaxLines) {
                    val endOffset = topParagraph.getLineEnd(0, visibleEnd = true)
                    remainingText.substring(0, endOffset) //+ if(topParagraph.hasVisualOverflow) "…" else ""
                } else {
                    remainingText
                }
            } else {
                ""
            }

            topOverflowText to bottomLineText
        }
    }

    Column(modifier = modifier) {
        if (topText.isNotEmpty()) {
            Text(
                text = topText,
                style = style,
                textAlign = TextAlign.End,
                overflow = TextOverflow.Clip,
                maxLines = 1,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Text(
            text = bottomText,
            style = style,
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            modifier = Modifier.align(Alignment.End)
        )
    }
}