package io.drdroid.amex.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.drdroid.amex.R
import io.drdroid.amex.ui.components.AppBar
import io.drdroid.amex.ui.components.Header
import io.drdroid.amex.ui.select_guests.Guests
import io.drdroid.amex.ui.theme.AMEXTheme
import io.drdroid.amex.utils.Utils

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

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var guests by remember { mutableStateOf(Utils.guestList.toMutableList()) }
    var btnState = remember {
        mutableStateOf(false)
    }
    var reservationState = remember {
        mutableStateOf(true)
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                AppBar(
                    title = "Select Guests",
                    scrollBehavior = scrollBehavior,
                    onNavigationIconClick = {

                    }
                )
            }
        },
        content = { innerPadding ->
            Guests(
                clients = guests,
                modifier = Modifier
                    .padding(innerPadding)
//                    .verticalScroll(state = rememberScrollState())
            ) {
                guests = it
//                Toast.makeText(
//                    context,
//                    "Value is ${it.any { x -> x.isSelected }}",
//                    Toast.LENGTH_SHORT
//                ).show()
                btnState.value = it.any { x -> x.isSelected }
                reservationState.value = it.none { x -> x.isSelected } ||
                        it.filter { x -> x.isSelected }.any { x -> x.hasReservation }
                Toast.makeText(context, "${reservationState.value}", Toast.LENGTH_SHORT).show()
            }
        },
        bottomBar = {
            if (reservationState.value) {
                Button(
                    enabled = btnState.value,
                    onClick = {
                        //go to next screen
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "Continue",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF00233C))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(9f)) {
                            Header(
                                text = "Reservation Needed",
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Select at least one Guest that has a reservation to continue.",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = TextUnit(24f, TextUnitType.Sp),
                                letterSpacing = TextUnit(-.35f, TextUnitType.Sp),
                            )
                        }
                        Icon(
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .weight(1f)
                                .clickable {
                                    reservationState.value = true
                                },
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AMEXTheme {
        MainScreen()
    }
}