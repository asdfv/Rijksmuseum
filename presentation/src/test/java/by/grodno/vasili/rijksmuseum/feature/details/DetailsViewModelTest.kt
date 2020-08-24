package by.grodno.vasili.rijksmuseum.feature.details

import androidx.lifecycle.Observer
import by.grodno.vasili.domain.error.RetrievingDataError
import by.grodno.vasili.domain.error.UnknownError
import by.grodno.vasili.domain.model.Details
import by.grodno.vasili.domain.usecase.GetDetailsUseCase
import by.grodno.vasili.domain.usecase.Result
import by.grodno.vasili.rijksmuseum.details
import by.grodno.vasili.rijksmuseum.util.InstantExecutorExtension
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class DetailsViewModelTest {
    @RelaxedMockK private lateinit var getDetailsUseCase: GetDetailsUseCase
    @InjectMockKs private lateinit var detailsViewModel: DetailsViewModel

    private val testDispatcher = Dispatchers.Unconfined

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadDetailsSuccess() {
        val objectNumber = "0sd_sSd"
        coEvery { getDetailsUseCase.execute(any()) } returns Result.Success(details(objectNumber))
        val spyObserver = spyObserver()
        val slot = slot<Result<Details>>()

        detailsViewModel.loadDetails(objectNumber).observeForever(spyObserver)

        verify { spyObserver.onChanged(capture(slot)) }
        val success = slot.captured as? Result.Success
        assertNotNull(success != null,
                      "GetDetailsUseCase case return success, but LiveData was not returned Result.Success")
        assertTrue(success?.data?.objectNumber == objectNumber, "Wrong object number in returned Result.Success.")
    }

    @Test
    fun loadDetailsFailed() {
        val objectNumber = "0sd_sSd"
        coEvery { getDetailsUseCase.execute(any()) } returns Result.Error(UnknownError())
        val spyObserver = spyObserver()
        val slot = slot<Result<Details>>()

        detailsViewModel.loadDetails(objectNumber).observeForever(spyObserver)

        verify { spyObserver.onChanged(capture(slot)) }
        val fail = slot.captured as? Result.Error
        assertNotNull(fail, "GetDetailsUseCase case return error, but LiveData was not returned Result.Error.")
        assertTrue(fail?.exception is UnknownError, "Wrong Error was returned by LiveData.")
    }

    @Test
    fun loadDetailsEmptyObjectNumber() {
        val objectNumber = ""
        val spyObserver = spyObserver()
        val slot = slot<Result<Details>>()

        detailsViewModel.loadDetails(objectNumber).observeForever(spyObserver)

        coVerify(exactly = 0) { getDetailsUseCase.execute(any()) }
        verify { spyObserver.onChanged(capture(slot)) }
        val fail = slot.captured as? Result.Error
        assertNotNull(fail, "LiveData was not returned Result.Error for empty object number.")
        assertTrue(fail?.exception is RetrievingDataError, "Wrong Error was returned by LiveData.")
    }

    private fun spyObserver(): Observer<Result<Details>> =
            spyk(Observer { })
}
