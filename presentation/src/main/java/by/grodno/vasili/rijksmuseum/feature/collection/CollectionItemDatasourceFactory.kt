package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetCollectionUseCase

/**
 * Factory to create [CollectionItemDatasource] and post it in LiveData.
 */
class CollectionItemDatasourceFactory(
        private val getCollectionUseCase: GetCollectionUseCase
) : DataSource.Factory<Int, ArtObject>() {
    val dataSourceLiveData: MutableLiveData<PageKeyedDataSource<Int, ArtObject>> = MutableLiveData()

    override fun create(): DataSource<Int, ArtObject> {
        val datasource = CollectionItemDatasource(getCollectionUseCase)
        dataSourceLiveData.postValue(datasource)
        return datasource
    }
}
