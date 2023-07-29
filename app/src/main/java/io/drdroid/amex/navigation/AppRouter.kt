package io.drdroid.amex.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import io.drdroid.amex.navigation.Destinations.CONFIRMATION
import io.drdroid.amex.navigation.Destinations.CONFLICT
import io.drdroid.amex.navigation.Destinations.GUESTS

object Destinations {

    const val GUESTS = "Guests"
    const val CONFIRMATION = "Confirmation"
    const val CONFLICT = "Conflict"

}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToGuest() {
        navController.navigate(GUESTS) {
            popUpTo(GUESTS)
        }
    }

    fun navigateToConfirmation() {
        navController.navigate(CONFIRMATION) {
            popUpTo(CONFIRMATION)
        }
    }

    fun navigateToConflict() {
        navController.navigate(CONFLICT) {
            popUpTo(CONFLICT)
        }
    }
}