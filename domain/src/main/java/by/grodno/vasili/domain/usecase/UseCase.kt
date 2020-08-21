package by.grodno.vasili.domain.usecase

import by.grodno.vasili.domain.error.DefaultErrorConverter
import by.grodno.vasili.domain.error.ErrorConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * Base use case for application needs.
 * Execute some action with [Params] in [Dispatchers.IO] scope and return the result with [Type].
 * Don't forget to [dispose] execution if you don't need it anymore.
 */
abstract class UseCase<Params, Type>(
        private val errorConverter: ErrorConverter = DefaultErrorConverter()
) : CoroutineScope by CoroutineScope(Dispatchers.IO) {

    /**
     * Action for retrieving data.
     */
    protected abstract suspend fun action(params: Params): Type

    /**
     * Run action.
     */
    suspend fun execute(params: Params): Result<Type> = try {
        Result.Success(action(params))
    } catch (e: Throwable) {
        Result.Error(errorConverter.convert(e))
    }

    /**
     * Cancel use case execution.
     */
    fun dispose() {
        cancel()
    }
}

/**
 * Wrapper for result of requested data.
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}
