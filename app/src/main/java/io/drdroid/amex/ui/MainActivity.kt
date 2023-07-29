package io.drdroid.amex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.drdroid.amex.data.vm.GuestViewModel
import io.drdroid.amex.ui.components.AppNavGraph
import io.drdroid.amex.ui.theme.AMEXTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var repository: Repository

    private val viewModel: GuestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AMEXTheme {
                viewModel.getGuestList(50)
                AppNavGraph(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AMEXTheme {
//        AppNavGraph(null)
    }
}