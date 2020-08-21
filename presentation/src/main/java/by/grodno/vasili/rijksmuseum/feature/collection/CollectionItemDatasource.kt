package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.paging.PageKeyedDataSource
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import by.grodno.vasili.domain.usecase.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Datasource for pagination.
 */
class CollectionItemDatasource(
        private val getCollectionUseCase: GetCollectionUseCase
) : PageKeyedDataSource<Int, ArtObject>(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, ArtObject>
    ) {
        launch {
            val result = getCollectionUseCase.execute(GetCollectionUseCase.Params(1))
            // todo Send error to UI, seems like better is to change paging to 3 version
            when (result) {
                is Result.Success -> callback.onResult(result.data, null, 2)
                is Result.Error -> Timber.e(result.exception, "Error while loading collection.")
            }
        }
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, ArtObject>
    ) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArtObject>) {
        launch {
            val page = params.key
            val result = getCollectionUseCase.execute(GetCollectionUseCase.Params(page))
            // todo if error react from UI?
            when (result) {
                is Result.Success -> callback.onResult(result.data, page + 1)
                is Result.Error -> Timber.e(result.exception, "Error while loading collection.")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        getCollectionUseCase.dispose()
        cancel()
    }
}
