package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import by.grodno.vasili.domain.model.ArtObject
import timber.log.Timber
import java.util.concurrent.Executors

/**
 * View model for activity witch present list of [ArtObject]s.
 */
internal class CollectionViewModel(
        private val sourceFactory: CollectionItemDatasourceFactory
) : ViewModel() {
    private val dataSourceLiveData: MutableLiveData<PageKeyedDataSource<Int, ArtObject>> =
            sourceFactory.dataSourceLiveData

    val pagedListLiveData: LiveData<PagedList<ArtObject>> by lazy {
        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .build()
        LivePagedListBuilder(sourceFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }

    /**
     * Invalidate datasource with [ArtObject]s.
     */
    fun invalidateDatasource() {
        val datasource = dataSourceLiveData.value
        if (datasource != null) {
            datasource.invalidate()
        } else {
            Timber.w("DatasourceLiveData not contains datasource")
        }
    }

    override fun onCleared() {
        invalidateDatasource()
    }
}
