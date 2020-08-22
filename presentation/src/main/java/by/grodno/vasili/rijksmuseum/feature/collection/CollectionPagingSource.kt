package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.paging.PagingSource
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import by.grodno.vasili.domain.usecase.GetCollectionUseCase.Params
import by.grodno.vasili.domain.usecase.Result

/**
 * Datasource for paginated items.
 */
class CollectionPagingSource(
        private val getCollectionUseCase: GetCollectionUseCase
) : PagingSource<Int, ArtObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtObject> {
        val nextPageNumber = params.key ?: 1
        return when (val result = getCollectionUseCase.execute(Params(nextPageNumber))) {
            is Result.Error -> LoadResult.Error(result.exception)
            is Result.Success -> LoadResult.Page(
                    data = result.data,
                    prevKey = null,
                    nextKey = nextPageNumber + 1
            )
        }
    }

    override fun invalidate() {
        super.invalidate()
        getCollectionUseCase.dispose()
    }
}
