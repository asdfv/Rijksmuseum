package by.grodno.vasili.rijksmuseum.feature.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import by.grodno.vasili.domain.error.RetrievingDataError
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetDetailsUseCase
import by.grodno.vasili.domain.usecase.Result

/**
 * View model for activity witch present [ArtObject]`s details.
 */
internal class DetailsViewModel @ViewModelInject constructor(
        private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    fun loadDetails(objectNumber: String?) = liveData {
        if (objectNumber == null) {
            emit(Result.Error(RetrievingDataError(message = "Object number is null.")))
        } else {
            val details = getDetailsUseCase.execute(GetDetailsUseCase.Params(objectNumber))
            emit(details)
        }
    }

    override fun onCleared() {
        getDetailsUseCase.dispose()
    }
}
