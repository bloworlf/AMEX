package io.drdroid.amex.data.impl

import io.drdroid.amex.data.network.ApiCall
import io.drdroid.amex.data.repo.Repository
import io.drdroid.amex.utils.Utils
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryImplTest {

    @Mock
    lateinit var apiCall: ApiCall

    lateinit var repository: Repository

    @Before
    fun onStart() {
        MockitoAnnotations.openMocks(this)

        repository = RepositoryImpl(
            apiCall = apiCall
        )
    }

    @After
    fun onFinish() {
        Mockito.clearAllCaches()
    }

    @Test
    fun getGuestList() = runBlocking {
        Mockito.`when`(apiCall.getGuestList(5)).thenReturn(
            Utils.getListGuest(5)
        )
        assertEquals(Utils.getListGuest(5).size, repository.getGuestList(5).size)
    }

    @Test
    fun getGuestList_Empty() = runBlocking {
        Mockito.`when`(apiCall.getGuestList(5)).thenReturn(
            listOf()
        )
        assertEquals(0, repository.getGuestList(5).size)
    }

    @Test
    fun getGuestList_Null() = runBlocking {
        Mockito.`when`(apiCall.getGuestList(5)).thenReturn(
            null
        )
        assertNull(repository.getGuestList(5))
    }
}