package by.grodno.vasili.data.repository

import by.grodno.vasili.data.datasource.CollectionDatasource
import by.grodno.vasili.domain.model.Collection
import by.grodno.vasili.domain.repository.CollectionRepository

/**
 * Repository for work with Collection.
 */
class CollectionDataRepository(
        private val collectionDatasource: CollectionDatasource
) : CollectionRepository {

    override suspend fun getCollection(page: Int): Collection {
        return collectionDatasource.getCollection(page).toDomainModel()
    }
}
