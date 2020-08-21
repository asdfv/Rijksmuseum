package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModel factory for [CollectionActivity].
 */
internal class CollectionViewModelFactory(
        private val sourceFactory: CollectionItemDatasourceFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CollectionViewModel(sourceFactory) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }
}
