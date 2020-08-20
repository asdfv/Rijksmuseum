package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.response.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service for work with collection and items.
 */
interface CollectionApiService {
    /**
     * Get page number [page] from within collection.
     */
    @GET("nl/collection")
    suspend fun getCollection(
        @Query("p") page: Int,
        @Query("ps") itemsPerPage: Int = 20,
    ): CollectionResponse
}
