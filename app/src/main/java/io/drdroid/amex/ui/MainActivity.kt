package io.drdroid.amex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.drdroid.amex.ui.components.AppNavGraph
import io.drdroid.amex.ui.theme.AMEXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AMEXTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    AppNavGraph()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AMEXTheme {
        MainScreen()
    }
}