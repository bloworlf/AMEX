package io.drdroid.amex.ui.conflict

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Conflict(modifier: Modifier) {
    Text(
        text = "Conflict Screen",
        modifier = modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(900),
    )
}