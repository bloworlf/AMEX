package io.drdroid.amex.data.impl

import io.drdroid.amex.data.model.client.GuestModel
import io.drdroid.amex.data.repo.Repository
import javax.inject.Inject
import kotlin.random.Random

class RepositoryImpl @Inject constructor() : Repository {

    override fun getGuestList(): List<GuestModel> = guestList

    private val random = Random(System.currentTimeMillis())

    private val guestList: List<GuestModel> = (1..50).map { index ->
        GuestModel(
            id = index,
            firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            hasReservation = random.nextBoolean(),
            isSelected = false
        )
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