package io.drdroid.amex.ui.confirmation

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Confirmation(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Confirmation Screen",
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(900),
        )
    }
}