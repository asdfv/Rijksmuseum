package by.grodno.vasili.data.error

import by.grodno.vasili.domain.error.*
import retrofit2.HttpException
import java.net.HttpURLConnection.*
import java.net.SocketTimeoutException

/**
 * [ErrorConverter] for data layer.
 */
class DataErrorConverter : ErrorConverter {
    override fun convert(error: Throwable): RijksThrowable {
        return when (error) {
            is HttpException -> getHttpError(error)
            is SocketTimeoutException -> Timeout(error)
            else -> RetrievingDataError(error)
        }
    }

    private fun getHttpError(error: HttpException): RijksThrowable {
        return when (error.code()) {
            HTTP_UNAUTHORIZED, HTTP_INTERNAL_ERROR -> WrongApiKey(error, "Probably wrong or missing IP Key.")
            HTTP_NOT_FOUND -> NotFound(error)
            else -> RetrievingDataError(error)
        }
    }
}
