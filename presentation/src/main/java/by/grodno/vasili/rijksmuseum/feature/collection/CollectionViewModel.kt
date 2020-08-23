package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import kotlinx.coroutines.flow.map

/**
 * View model for activity witch present list of [ArtObject]s.
 */
internal class CollectionViewModel @ViewModelInject constructor(
        getCollectionUseCase: GetCollectionUseCase
) : ViewModel() {
    private lateinit var collectionPagingSource: CollectionPagingSource

    /**
     * Flow with paginated [ArtObject]s to subscribe in UI.
     */
    val artObjectsFlow = Pager(PagingConfig(pageSize = 10)) {
        collectionPagingSource = CollectionPagingSource(getCollectionUseCase)
        collectionPagingSource
    }.flow
            .map { artObjectPagingData ->
                artObjectPagingData.map { artObject -> UiModel.ArtObjectItem(artObject) }
            }
            .map { uiModelPagingData ->
                uiModelPagingData.insertSeparators { before, after ->
                    if (hasNextPrincipalDifferentFirstLetter(after, before)) {
                        UiModel.SeparatorItem(after.principalFirstLetter()?.toUpperCase().toString())
                    } else {
                        null
                    }
                }
            }
            .cachedIn(viewModelScope)

    private fun hasNextPrincipalDifferentFirstLetter(
            after: UiModel.ArtObjectItem?,
            before: UiModel.ArtObjectItem?
    ) = after != null
            && (before == null || before.principalFirstLetter() !== after.principalFirstLetter())

    private fun UiModel.ArtObjectItem?.principalFirstLetter(): Char? = this?.artObject?.principalOrFirstMaker?.firstOrNull()

    /**
     * Invalidate datasource with [ArtObject]s.
     */
    fun invalidateDatasource() {
        if (this::collectionPagingSource.isInitialized) collectionPagingSource.invalidate()
    }

    override fun onCleared() {
        invalidateDatasource()
    }
}

/**
 * Art object and separator wrapper for paginated flow.
 */
sealed class UiModel {
    data class ArtObjectItem(val artObject: ArtObject) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}
