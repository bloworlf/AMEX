package io.drdroid.amex.data.impl

import io.drdroid.amex.data.model.client.GuestModel
import io.drdroid.amex.data.network.ApiCall
import io.drdroid.amex.data.repo.Repository
import io.drdroid.amex.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import javax.inject.Inject
import kotlin.random.Random

class RepositoryImpl @Inject constructor(
    private val apiCall: ApiCall
) : Repository {

    override suspend fun getGuestList(max: Int): List<GuestModel> {
        var amount = max
        if (amount < 1) {
            amount = 50
        }
        withContext(Dispatchers.IO) {
            sleep(Random(System.currentTimeMillis()).nextLong(5000))
        }
//        return Utils.getListGuest(amount)
        return apiCall.getGuestList(amount)
    }
}