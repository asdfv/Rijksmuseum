package by.grodno.vasili.rijksmuseum.feature.collection

import androidx.recyclerview.widget.DiffUtil
import by.grodno.vasili.data.datasource.retrofit.RetrofitCollectionDatasource
import by.grodno.vasili.data.error.DataErrorConverter
import by.grodno.vasili.data.repository.CollectionDataRepository
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import by.grodno.vasili.rijksmuseum.BuildConfig

/**
 * Dependency factory for [CollectionActivity].
 */
internal class CollectionDependenciesModule {
    val factory: CollectionViewModelFactory
    val adapter: CollectionAdapter

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ArtObject> =
                object : DiffUtil.ItemCallback<ArtObject>() {
                    override fun areItemsTheSame(oldItem: ArtObject, newItem: ArtObject): Boolean {
                        return oldItem.id == newItem.id
                    }

                    override fun areContentsTheSame(oldItem: ArtObject, newItem: ArtObject): Boolean {
                        return oldItem == newItem
                    }
                }
    }

    init {
        val getCollectionUseCase =
                GetCollectionUseCase(CollectionDataRepository(RetrofitCollectionDatasource(BuildConfig.API_KEY)), DataErrorConverter())
        factory = CollectionViewModelFactory(getCollectionUseCase)
        adapter = CollectionAdapter(DIFF_CALLBACK)
    }
}
