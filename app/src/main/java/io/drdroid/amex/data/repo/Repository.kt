package io.drdroid.amex.data.repo

import io.drdroid.amex.data.model.client.GuestModel
import kotlin.random.Random

interface Repository {

    suspend fun getGuestList(max: Int): List<GuestModel>
}