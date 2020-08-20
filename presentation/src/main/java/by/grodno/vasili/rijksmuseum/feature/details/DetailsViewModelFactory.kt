package by.grodno.vasili.rijksmuseum.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.grodno.vasili.domain.usecase.GetDetailsUseCase

/**
 * ViewModel factory for [DetailsActivity].
 */
internal class DetailsViewModelFactory(
        private val getDetailsUseCase: GetDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(getDetailsUseCase) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }
}
