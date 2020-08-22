package by.grodno.vasili.rijksmuseum.feature.collection

import by.grodno.vasili.data.datasource.retrofit.RetrofitCollectionDatasource
import by.grodno.vasili.data.error.DataErrorConverter
import by.grodno.vasili.data.repository.CollectionDataRepository
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import by.grodno.vasili.rijksmuseum.BuildConfig

/**
 * Dependency factory for [CollectionActivity].
 */
internal class CollectionDependenciesModule {
    val factory: CollectionViewModelFactory
    val adapter: CollectionAdapter

    init {
        val getCollectionUseCase =
                GetCollectionUseCase(CollectionDataRepository(RetrofitCollectionDatasource(BuildConfig.API_KEY)),
                                     DataErrorConverter())
        factory = CollectionViewModelFactory(getCollectionUseCase)
        adapter = CollectionAdapter()
    }
}
