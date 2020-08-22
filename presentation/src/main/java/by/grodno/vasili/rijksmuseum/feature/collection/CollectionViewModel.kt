package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetCollectionUseCase

/**
 * View model for activity witch present list of [ArtObject]s.
 */
internal class CollectionViewModel(getCollectionUseCase: GetCollectionUseCase) : ViewModel() {
    private lateinit var collectionPagingSource: CollectionPagingSource

    /**
     * Flow with paginated [ArtObject]s to subscribe in UI.
     */
    val artObjectsFlow = Pager(PagingConfig(pageSize = 10)) {
        collectionPagingSource = CollectionPagingSource(getCollectionUseCase)
        collectionPagingSource
    }.flow.cachedIn(viewModelScope)

    /**
     * Invalidate datasource with [ArtObject]s.
     */
    fun invalidateDatasource() {
        collectionPagingSource.invalidate()
    }

    override fun onCleared() {
        invalidateDatasource()
    }
}
