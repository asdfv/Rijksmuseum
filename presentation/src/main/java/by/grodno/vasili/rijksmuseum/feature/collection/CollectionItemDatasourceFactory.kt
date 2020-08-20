package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import by.grodno.vasili.domain.model.ArtObject
import kotlinx.coroutines.CoroutineScope

/**
 * Factory to create [CollectionItemDatasource] and post it in LiveData.
 */
class CollectionItemDatasourceFactory(
    private val getCollectionUseCase: GetCollectionUseCase,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, ArtObject>() {
    val dataSourceLiveData: MutableLiveData<PageKeyedDataSource<Int, ArtObject>> = MutableLiveData()

    override fun create(): DataSource<Int, ArtObject> {
        val datasource = CollectionItemDatasource(getCollectionUseCase, scope)
        dataSourceLiveData.postValue(datasource)
        return datasource
    }
}
