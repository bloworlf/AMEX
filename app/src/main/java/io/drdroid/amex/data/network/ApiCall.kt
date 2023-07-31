package io.drdroid.amex.data.network

import io.drdroid.amex.data.model.client.GuestModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET("guests")
    suspend fun getGuestList(
        @Query("max") max: Int
    ): List<GuestModel>
}