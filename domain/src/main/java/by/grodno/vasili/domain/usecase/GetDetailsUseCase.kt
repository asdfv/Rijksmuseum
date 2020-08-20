package by.grodno.vasili.domain.usecase

import by.grodno.vasili.domain.model.Details
import by.grodno.vasili.domain.repository.CollectionRepository

/**
 * Use case for retrieving art object details.
 */
class GetDetailsUseCase(
        private val repository: CollectionRepository
) : UseCase<GetDetailsUseCase.Params, Details>() {

    /**
     * Parameters for request.
     */
    data class Params(val objectNumber: String)

    override suspend fun action(params: Params): Details {
        return repository.getDetails(params.objectNumber)
    }
}
