package by.grodno.vasili.data.datasource.retrofit

import by.grodno.vasili.data.BuildConfig
import by.grodno.vasili.data.datasource.CollectionDatasource
import by.grodno.vasili.data.response.CollectionResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [CollectionDatasource] implementation for work with museum collection via Retrofit.
 */
class RetrofitCollectionDatasource(
    private val key: String
) : CollectionDatasource {
    private val collectionApiService: CollectionApiService = Retrofit.Builder()
        .baseUrl("https://www.rijksmuseum.nl/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(buildOkHttpClient())
        .build()
        .create(CollectionApiService::class.java)

    private fun buildOkHttpClient(): OkHttpClient {
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
        val logingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(queryInterceptor)
            .addInterceptor(logingInterceptor)
            .build()
    }

    override suspend fun getCollection(page: Int): CollectionResponse =
        collectionApiService.getCollection(page)
}
