package io.drdroid.amex.data.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.drdroid.amex.data.model.client.GuestModel
import io.drdroid.amex.data.repo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GuestViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val liveData: MutableLiveData<MutableList<GuestModel>> by lazy {
        MutableLiveData<MutableList<GuestModel>>()
    }

    fun getGuestList(max: Int = Random(50).nextInt()) {
        CoroutineScope(Dispatchers.Main).launch {
            val value = repository.getGuestList(max)
            liveData.postValue(value.toMutableList())
        }
    }
}