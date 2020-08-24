package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.datasource.CollectionDatasource
import by.grodno.vasili.data.response.CollectionResponse
import by.grodno.vasili.data.response.DetailsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [CollectionDatasource] implementation for work with museum collection via Retrofit.
 * Specify non empty [key] for using it as a query url parameter: key=<your_key>.
 * [enableLogs] for enabling request/response logging.
 */
class RetrofitCollectionDatasource(
        private val key: String = "",
        private val enableLogs: Boolean = true,
) : CollectionDatasource {

    private val collectionApiService: CollectionApiService = Retrofit.Builder()
            .baseUrl("https://www.rijksmuseum.nl/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(buildOkHttpClient())
            .build()
            .create(CollectionApiService::class.java)

    private fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (key.isNotBlank()) {
            val queryInterceptor = Interceptor { chain ->
                var request = chain.request()
                val url = request.url.newBuilder()
                        .addQueryParameter("key", key)
                        .build()
                request = request.newBuilder()
                        .url(url)
                        .build()
                chain.proceed(request)
            }
            builder.addInterceptor(queryInterceptor)
        }
        if (enableLogs) {
            val logingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            builder.addInterceptor(logingInterceptor)
        }
        return builder.build()
    }

    override suspend fun getCollection(page: Int): CollectionResponse =
            collectionApiService.getCollection(page)

    override suspend fun getDetails(objectNumber: String): DetailsResponse =
            collectionApiService.getDetails(objectNumber)
}
