package io.drdroid.amex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

@Composable
fun CircleText(modifier: Modifier, text: String, backgroundColor: Color) {
    Text(
//        modifier = Modifier
//            .padding(16.dp)
//            .drawBehind {
//                drawCircle(
//                    color = color,
//                    radius = this.size.maxDimension
//                )
//            },
        color = Color.White,
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape)
//            .aspectRatio(1f)
//            .circleLayout()
            .padding(8.dp),
        text = text,
    )
}

fun Modifier.circleLayout() =
    layout { measurable, constraints ->
        // Measure the composable
        val placeable = measurable.measure(constraints)

        //get the current max dimension to assign width=height
        val currentHeight = placeable.height
        val currentWidth = placeable.width
        val newDiameter = maxOf(currentHeight, currentWidth)

        //assign the dimension and the center position
        layout(newDiameter, newDiameter) {
            // Where the composable gets placed
            placeable.placeRelative(
                (newDiameter - currentWidth) / 2,
                (newDiameter - currentHeight) / 2
            )
        }
    }