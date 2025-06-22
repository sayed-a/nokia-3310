package nostalgia.devices.nokia3310.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.LayoutDirection.Ltr
import nostalgia.devices.nokia3310.shell.KeypadColumn
import kotlin.math.min

class KeypadButtonShape(column: KeypadColumn): CornerBasedShape(
    topStart = CornerSize(column.topLeft),
    topEnd = CornerSize(column.topRight),
    bottomEnd = CornerSize(column.bottomRight),
    bottomStart = CornerSize(column.bottomLeft)
) {

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        return Outline.Rounded(
            RoundRect(
                rect = size.toRect(),
                topLeft = CornerRadius(if (layoutDirection == Ltr) topStart else topEnd),
                topRight = CornerRadius(if (layoutDirection == Ltr) topEnd else topStart),
                bottomRight = CornerRadius(if (layoutDirection == Ltr) bottomEnd else bottomStart),
                bottomLeft = CornerRadius(if (layoutDirection == Ltr) bottomStart else bottomEnd)
            )
        )
    }


    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ) = RoundedCornerShape(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart
    )

    override fun toString(): String {
        return "KeypadButtonShape(topStart = $topStart, topEnd = $topEnd, bottomEnd = " +
                "$bottomEnd, bottomStart = $bottomStart)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoundedCornerShape) return false

        if (topStart != other.topStart) return false
        if (topEnd != other.topEnd) return false
        if (bottomEnd != other.bottomEnd) return false
        if (bottomStart != other.bottomStart) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topStart.hashCode()
        result = 31 * result + topEnd.hashCode()
        result = 31 * result + bottomEnd.hashCode()
        result = 31 * result + bottomStart.hashCode()
        return result
    }
}

val MenuButtonShape = RoundedCornerShape(CornerSize(50), CornerSize(50), CornerSize(100), CornerSize(100))

class CancelButtonShape(
    private val curvatureFactor: Float = 0.2f,
    private val cornerRadiusValue: Float
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val longSide = size.width
            val shortSide = size.height
            val curvatureAmount = longSide * curvatureFactor

            val r = min(cornerRadiusValue, min(longSide / 2f, shortSide / 2f))

            // Start after top-left rounded corner's arc
            moveTo(r, 0f)

            // Top long edge: CONVEX (bulges outwards/upwards)
            // The quadraticBezierTo now needs to connect from after the top-left corner
            // to before the top-right corner.
            // For simplicity, we keep the control point relative to the original full length,
            // but this might need adjustment for perfect smoothness with large radii.
            quadraticBezierTo(
                x1 = longSide / 2f,
                y1 = -curvatureAmount,
                x2 = longSide - r, // End before top-right corner radius
                y2 = 0f
            )

            // Top-right rounded corner
            arcTo(
                rect = Rect(
                    left = longSide - 2 * r,
                    top = 0f,
                    right = longSide,
                    bottom = 2 * r
                ),
                startAngleDegrees = -90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Right short straight edge (shortened)
            lineTo(longSide, shortSide - r)

            // Bottom-right rounded corner
            arcTo(
                rect = Rect(
                    left = longSide - 2 * r,
                    top = shortSide - 2 * r,
                    right = longSide,
                    bottom = shortSide
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Bottom long edge: CONCAVE (curves inwards/upwards towards the center)
            // Adjust start/end points for corner radii
            quadraticBezierTo(
                x1 = longSide / 2f,
                y1 = shortSide - curvatureAmount, // Control point relative to full length
                x2 = r, // End after bottom-left corner radius
                y2 = shortSide
            )

            // Bottom-left rounded corner
            arcTo(
                rect = Rect(
                    left = 0f,
                    top = shortSide - 2 * r,
                    right = 2 * r,
                    bottom = shortSide
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Left short straight edge (shortened)
            lineTo(0f, r)

            // Top-left rounded corner
            arcTo(
                rect = Rect(
                    left = 0f,
                    top = 0f,
                    right = 2 * r,
                    bottom = 2 * r
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            close()
        }
        return Outline.Generic(path)
    }
}

class HorizontalOvalShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            addOval(Rect(left = 0f, top = 0f, right = size.width, bottom = size.height))
        }
        return Outline.Generic(path)
    }
}