package io.drdroid.amex.utils

import io.drdroid.amex.data.model.client.GuestModel
import kotlin.random.Random

object Utils {

//    val guestList: List<GuestModel> = listOf(
//        GuestModel(
//            id = 1,
//            firstName = "Dale",
//            lastName = "Gibson",
//            hasReservation = true,
//            isSelected = false
//        ),
//        GuestModel(
//            id = 2,
//            firstName = "Jeremy",
//            lastName = "Gibson",
//            hasReservation = true,
//            isSelected = false
//        ),
//        GuestModel(
//            id = 3,
//            firstName = "Linda",
//            lastName = "Gibson",
//            hasReservation = false,
//            isSelected = false
//        ),
//        GuestModel(
//            id = 4,
//            firstName = "Margaret",
//            lastName = "Gibson",
//            hasReservation = false,
//            isSelected = false
//        ),
//    )

    private val random = Random(System.currentTimeMillis())

    fun getListGuest(max:Int):List<GuestModel>{
        return (1..max).map { index ->
            GuestModel(
                id = index,
                firstName = getRandomFirstName(),
                lastName = getRandomLastName(),
                hasReservation = random.nextBoolean(),
                isSelected = false
            )
        }
    }

    private fun getRandomFirstName(): String {
        val firstNames = listOf(
            "John", "Alice", "Michael", "Emily", "David", "Sophia", "Robert", "Emma",
            "William", "Olivia", "James", "Ava", "Joseph", "Isabella", "Daniel", "Mia"
            // Add more names as needed
        )
        return firstNames[random.nextInt(firstNames.size)]
    }

    private fun getRandomLastName(): String {
        val lastNames = listOf(
            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson",
            "Taylor", "Clark", "White", "Lewis", "Lee", "Walker", "Hall", "Allen"
            // Add more names as needed
        )
        return lastNames[random.nextInt(lastNames.size)]
    }
}