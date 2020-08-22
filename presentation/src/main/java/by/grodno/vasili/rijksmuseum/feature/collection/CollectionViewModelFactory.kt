package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.grodno.vasili.domain.usecase.GetCollectionUseCase

/**
 * ViewModel factory for [CollectionActivity].
 */
internal class CollectionViewModelFactory(
        private val getCollectionUseCase: GetCollectionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CollectionViewModel(getCollectionUseCase) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }
}
