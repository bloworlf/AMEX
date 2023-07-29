package io.drdroid.amex.data.network

import io.drdroid.amex.data.model.client.GuestModel
import retrofit2.http.GET

interface ApiCall {

    @GET
    suspend fun getGuestList(max: Int): List<GuestModel>
}