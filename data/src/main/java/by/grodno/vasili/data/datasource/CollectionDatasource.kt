package by.grodno.vasili.data.datasource

import by.grodno.vasili.data.response.CollectionResponse
import by.grodno.vasili.data.response.DetailsResponse

/**
 * Datasource for work with collection and items.
 */
interface CollectionDatasource {
    /**
     * Get collection with items from [page].
     */
    suspend fun getCollection(page: Int): CollectionResponse

    /**
     * Get art object details by [objectNumber].
     */
    suspend fun getDetails(objectNumber: String): DetailsResponse
}
