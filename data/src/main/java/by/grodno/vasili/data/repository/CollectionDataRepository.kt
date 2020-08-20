package by.grodno.vasili.data.repository

import by.grodno.vasili.data.datasource.CollectionDatasource
import by.grodno.vasili.domain.model.Collection
import by.grodno.vasili.domain.model.Details
import by.grodno.vasili.domain.repository.CollectionRepository

/**
 * Repository for work with Collection.
 */
class CollectionDataRepository(
        private val collectionDatasource: CollectionDatasource
) : CollectionRepository {

    override suspend fun getCollection(page: Int): Collection =
            collectionDatasource.getCollection(page).toDomainModel()

    /**
     * Get art object details by [objectNumber].
     */
    override suspend fun getDetails(objectNumber: String): Details =
            collectionDatasource.getDetails(objectNumber).toDomainModel()
}
