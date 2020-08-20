package by.grodno.vasili.domain.repository

import by.grodno.vasili.domain.model.Collection
import by.grodno.vasili.domain.model.Details

/**
 * Implement this interface for work with [Collection].
 */
interface CollectionRepository {
    /**
     * Take [Collection] with [page] of items inside.
     */
    suspend fun getCollection(page: Int): Collection

    /**
     * Get art object details by [objectNumber].
     */
    suspend fun getDetails(objectNumber: String): Details
}
