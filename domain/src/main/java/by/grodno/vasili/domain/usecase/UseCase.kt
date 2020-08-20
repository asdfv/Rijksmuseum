package by.grodno.vasili.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * Base use case for application needs.
 * Execute some action with [Params] in [Dispatchers.IO] scope and return the result with [Type].
 * Don't forget to [dispose] execution if you don't need it anymore.
 */
abstract class UseCase<Params, Type> : CoroutineScope by CoroutineScope(Dispatchers.IO) {

    /**
     * Action for retrieving data.
     */
    protected abstract suspend fun action(params: Params): Type

    /**
     * Run action.
     */
    suspend fun execute(params: Params): Type = action(params)

    /**
     * Cancel use case execution.
     */
    fun dispose() {
        cancel()
    }
}
