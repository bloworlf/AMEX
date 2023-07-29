package io.drdroid.amex.data.repo

import io.drdroid.amex.data.model.client.GuestModel

interface Repository {

    fun getGuestList(): List<GuestModel>
}