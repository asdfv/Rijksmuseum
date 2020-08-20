package by.grodno.vasili.domain.repository

import by.grodno.vasili.domain.model.Collection

/**
 * Implement this interface for work with [Collection].
 */
interface CollectionRepository {
    /**
     * Take [Collection] with [page] of items inside.
     */
    suspend fun getCollection(page: Int): Collection
}
