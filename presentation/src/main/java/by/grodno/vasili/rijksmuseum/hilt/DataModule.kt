package by.grodno.vasili.rijksmuseum.hilt

import by.grodno.vasili.data.datasource.CollectionDatasource
import by.grodno.vasili.data.datasource.retrofit.RetrofitCollectionDatasource
import by.grodno.vasili.data.error.DataErrorConverter
import by.grodno.vasili.data.repository.CollectionDataRepository
import by.grodno.vasili.domain.error.ErrorConverter
import by.grodno.vasili.domain.repository.CollectionRepository
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import by.grodno.vasili.domain.usecase.GetDetailsUseCase
import by.grodno.vasili.rijksmuseum.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Hilt module for data layer dependencies.
 */
@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCollectionDatasource(): CollectionDatasource =
            RetrofitCollectionDatasource(BuildConfig.API_KEY)

    @Provides
    @Singleton
    fun provideCollectionRepository(datasource: CollectionDatasource): CollectionRepository =
            CollectionDataRepository(datasource)

    @Provides
    @Singleton
    fun provideErrorConverter(): ErrorConverter = DataErrorConverter()

    @Provides
    @Singleton
    fun provideGetCollectionUseCase(
            repository: CollectionRepository,
            errorConverter: ErrorConverter
    ): GetCollectionUseCase = GetCollectionUseCase(repository, errorConverter)

    @Provides
    @Singleton
    fun provideDetailsUseCase(
            repository: CollectionRepository,
            errorConverter: ErrorConverter
    ): GetDetailsUseCase = GetDetailsUseCase(repository, errorConverter)
}
