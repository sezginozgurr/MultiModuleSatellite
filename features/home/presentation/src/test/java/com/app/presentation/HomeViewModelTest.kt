package com.app.presentation

import com.app.common.BaseException
import com.app.common.Resource
import com.app.domain.model.SatelliteListUiModel
import com.app.domain.repository.SatelliteRepository
import com.app.domain.usecase.SatelliteListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @mockK kullanarak veriler mocklanabilir
 * **/
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init triggers load and updates state on success`() = runTest(testDispatcher) {
        // Given
        val list = listOf(
            SatelliteListUiModel(id = 1, active = true, name = "A"),
            SatelliteListUiModel(id = 2, active = false, name = "B")
        )
        val repo = object : SatelliteRepository {
            override suspend fun getSatelliteList(): Resource<List<SatelliteListUiModel>> = Resource.Success(list)
        }
        val useCase = SatelliteListUseCase(repo)

        // When
        val vm = HomeViewModel(useCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = vm.uiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(list, state.satellites)
        assertEquals(null, state.error)
    }

    /**
     * isimlendirmeler given / when / then şeklinde de olabilir. Standartlar gözetilmelidir.
     * **/
    @Test
    fun given_error_from_repository_when_init_then_updates_state_and_emits_toast() = runTest(testDispatcher) {
        // Given
        val message = "boom"
        val repo = object : SatelliteRepository {
            override suspend fun getSatelliteList(): Resource<List<SatelliteListUiModel>> =
                Resource.Error(BaseException(message))
        }
        val useCase = SatelliteListUseCase(repo)

        // When
        val vm = HomeViewModel(useCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = vm.uiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(message, state.error)

        val effect = vm.uiEffect.first()
        assertEquals(HomeContract.HomeUiEffect.ShowToast(message), effect)
    }


    @Test
    fun `query changed updates state`() = runTest(testDispatcher) {
        // Given
        val repo = object : SatelliteRepository {
            override suspend fun getSatelliteList(): Resource<List<SatelliteListUiModel>> = Resource.Success(emptyList())
        }
        val vm = HomeViewModel(SatelliteListUseCase(repo))
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        vm.onAction(HomeContract.HomeUiAction.QueryChanged("abc"))

        // Then
        val state = vm.uiState.first()
        assertEquals("abc", state.query)
    }

    @Test
    fun `item clicked emits navigate effect`() = runTest(testDispatcher) {
        // Given
        val repo = object : SatelliteRepository {
            override suspend fun getSatelliteList(): Resource<List<SatelliteListUiModel>> = Resource.Success(emptyList())
        }
        val vm = HomeViewModel(SatelliteListUseCase(repo))
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        vm.onAction(HomeContract.HomeUiAction.ItemClicked(5, "Name"))

        // Then
        val effect = vm.uiEffect.first()
        assertEquals(HomeContract.HomeUiEffect.NavigateToDetails(5, "Name"), effect)
    }
}


