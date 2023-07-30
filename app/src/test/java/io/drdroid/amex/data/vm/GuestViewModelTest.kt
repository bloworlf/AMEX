package io.drdroid.amex.data.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.drdroid.amex.data.repo.Repository
import io.drdroid.amex.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GuestViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repository: Repository

    private lateinit var viewModel: GuestViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun onStart() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        viewModel = GuestViewModel(repository)
    }

    @After
    fun onFinish() {
        Mockito.clearAllCaches()
    }

    @Test
    fun getGuestList() = runTest {
        Mockito.`when`(repository.getGuestList(5)).thenReturn(Utils.getListGuest(5))

        viewModel.getGuestList(5)

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(Utils.getListGuest(5).size, viewModel.liveData.value?.size)
    }

    @Test
    fun getGuestList_Empty() = runTest {
        Mockito.`when`(repository.getGuestList(5)).thenReturn(listOf())

        viewModel.getGuestList(5)

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(listOf<GuestViewModel>(), viewModel.liveData.value)
    }

    @Test
    fun getGuestList_Null() = runTest {
        Mockito.`when`(repository.getGuestList(5)).thenReturn(null)

        viewModel.getGuestList(5)

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.liveData.value?.isEmpty() == true)
    }
}