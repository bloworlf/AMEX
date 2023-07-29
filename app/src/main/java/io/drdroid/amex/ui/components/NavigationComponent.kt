package io.drdroid.amex.ui.components

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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.drdroid.amex.R
import io.drdroid.amex.data.repo.Repository
import io.drdroid.amex.navigation.AppNavigationActions
import io.drdroid.amex.navigation.Destinations
import io.drdroid.amex.ui.confirmation.Confirmation
import io.drdroid.amex.ui.conflict.Conflict
import io.drdroid.amex.ui.guests.Guests

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navigationActions: AppNavigationActions,
    onSearchQueryChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                color = Color(0xFF00233C),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
//                    .fillMaxHeight()
                    .padding(start = 12.dp)
                    .clickable {
                        navigationActions.navigateToGuest()
                    },
                tint = Color.Blue,
                painter = painterResource(id = R.drawable.arrow_back),
//                imageVector = Icons.Filled.ArrowBack,
                contentDescription = ""
            )
        },
        actions = {
            SearchBar(
                keyboardController = keyboardController,
                onSearchQueryChanged = onSearchQueryChanged
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = Modifier
            .background(color = Color.White),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(repository: Repository?) {
    val navController: NavHostController = rememberNavController()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: Destinations.GUESTS
    val navigationActions = remember(navController) {
        AppNavigationActions(navController)
    }

    var guests by remember { mutableStateOf(repository!!.getGuestList().toMutableList()) }
    val btnState = remember {
        mutableStateOf(false)
    }
    val noReservationState = remember {
        mutableStateOf(false)
    }
    val queryText = remember {
        mutableStateOf("")
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                AppBar(
                    title = when (currentRoute) {
                        "Guests" -> {
                            "Select Guests"
                        }

                        "Confirmation" -> {
                            "Confirmation Screen"
                        }

                        "Conflict" -> {
                            "Conflict Screen"
                        }

                        else -> {
                            currentRoute
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    navigationActions = navigationActions,
                    onSearchQueryChanged = {
                        queryText.value = it
                    }
                )
            }
        },
        content = { innerPadding ->
            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = Destinations.GUESTS
            ) {
                composable(Destinations.GUESTS) {
                    Guests(
                        guests = guests,
                        queryText = queryText.value,
                        modifier = Modifier
                            .padding(innerPadding), onValueChanged = {
                            guests = it
                            btnState.value = it.any { x -> x.isSelected }
//                            noReservationState.value =
//                                it.none { x -> x.isSelected }
//                                        ||
//                                        it.filter { x -> x.isSelected }
//                                            .any { x -> x.hasReservation }
                        })
                }
                composable(Destinations.CONFIRMATION) {
                    Confirmation(modifier = Modifier.padding(innerPadding))
                }
                composable(Destinations.CONFLICT) {
                    Conflict(modifier = Modifier.padding(innerPadding))
                }
            }
        },
        bottomBar = {
            if (!noReservationState.value) {
                Button(
                    enabled = btnState.value,
                    onClick = {
//                        if (guests.filter { it.isSelected }.any { it.hasReservation }) {
//                            //confirmation screen
//                            navigationActions.navigateToConfirmation()
////                            navController.navigate("Confirmation")
//                        } else {
//                            //conflict screen
////                            navController.navigate("Conflict")
//                            navigationActions.navigateToConflict()
//                        }
                        if (currentRoute == "Conflict") {
                            navigationActions.navigateToGuest()
                        } else {
                            if (guests.filter { it.isSelected }.none { it.hasReservation }) {
                                //display snack
                                noReservationState.value = true
                            } else if (guests.filter { it.isSelected }.any { !it.hasReservation }) {
                                //conflict screen
                                navigationActions.navigateToConflict()
                            } else {
                                //confirmation screen
                                navigationActions.navigateToConfirmation()
                            }
                        }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = when (currentRoute) {
//                            "Guests" -> {
//                                "Continue"
//                            }

                            "Confirmation" -> {
                                "Done"
                            }

                            "Conflict" -> {
                                "Go Back"
                            }

                            else -> {
                                "Continue"
                            }
                        },
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
                                textColor = Color.White
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
                                    noReservationState.value = false
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