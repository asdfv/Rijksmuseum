package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.paging.PageKeyedDataSource
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Datasource for pagination.
 */
class CollectionItemDatasource(
        private val getCollectionUseCase: GetCollectionUseCase,
        private val scope: CoroutineScope
) : PageKeyedDataSource<Int, ArtObject>() {

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, ArtObject>
    ) {
        scope.launch {
            val artObjects = getCollectionUseCase.execute(GetCollectionUseCase.Params(1)).artObjects
            callback.onResult(artObjects, null, 2)
        }
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, ArtObject>
    ) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArtObject>) {
        scope.launch {
            val page = params.key
            val artObjects =
                    getCollectionUseCase.execute(GetCollectionUseCase.Params(page)).artObjects
            callback.onResult(artObjects, page + 1)
        }
    }
}
