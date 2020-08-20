package by.grodno.vasili.rijksmuseum.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetDetailsUseCase

/**
 * View model for activity witch present [ArtObject]`s details.
 */
internal class DetailsViewModel(
        private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    fun loadDetails(objectNumber: String) = liveData {
        val details = getDetailsUseCase.execute(GetDetailsUseCase.Params(objectNumber))
        emit(details)
    }

    override fun onCleared() {
        getDetailsUseCase.dispose()
    }
}
