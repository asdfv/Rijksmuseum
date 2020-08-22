package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.response.CollectionResponse
import by.grodno.vasili.data.response.DetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
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
            @Query("ps") itemsPerPage: Int = 10,
            @Query("imgonly") imageOnly: Boolean = true,
            @Query("s") sort: String = "artist",
    ): CollectionResponse

    /**
     * Get art object details by [objectNumber].
     */
    @GET("nl/collection/{objectNumber}")
    suspend fun getDetails(
            @Path("objectNumber") objectNumber: String
    ): DetailsResponse
}
