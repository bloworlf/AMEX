package io.drdroid.amex.ui.confirmation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Confirmation(modifier: Modifier) {
    Text(
        text = "Confirmation Screen",
        modifier = modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(900),
    )
}