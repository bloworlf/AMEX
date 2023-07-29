package io.drdroid.amex.data.repo

import io.drdroid.amex.data.model.client.GuestModel
import kotlin.random.Random

interface Repository {

    fun getGuestList(max: Int = Random(50).nextInt()): List<GuestModel>
}