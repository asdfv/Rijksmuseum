package by.grodno.vasili.rijksmuseum.feature.details

import by.grodno.vasili.data.datasource.retrofit.RetrofitCollectionDatasource
import by.grodno.vasili.data.repository.CollectionDataRepository
import by.grodno.vasili.domain.usecase.GetDetailsUseCase
import by.grodno.vasili.rijksmuseum.BuildConfig

/**
 * Dependency factory.
 */
internal class DetailsDependenciesModule {
    val factory: DetailsViewModelFactory by lazy {
        val getDetailsUseCase =
                GetDetailsUseCase(CollectionDataRepository(RetrofitCollectionDatasource(BuildConfig.API_KEY)))
        DetailsViewModelFactory(getDetailsUseCase)
    }
}
