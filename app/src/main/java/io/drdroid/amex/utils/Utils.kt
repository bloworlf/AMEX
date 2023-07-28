package io.drdroid.amex.utils

import io.drdroid.amex.data.model.client.GuestModel

object Utils {

    val guestList: List<GuestModel> = listOf(
        GuestModel(
            id = 1,
            firstName = "Dale",
            lastName = "Gibson",
            hasReservation = true,
            isSelected = false
        ),
        GuestModel(
            id = 2,
            firstName = "Jeremy",
            lastName = "Gibson",
            hasReservation = true,
            isSelected = false
        ),
        GuestModel(
            id = 3,
            firstName = "Linda",
            lastName = "Gibson",
            hasReservation = false,
            isSelected = false
        ),
        GuestModel(
            id = 4,
            firstName = "Margaret",
            lastName = "Gibson",
            hasReservation = false,
            isSelected = false
        ),


        )
}