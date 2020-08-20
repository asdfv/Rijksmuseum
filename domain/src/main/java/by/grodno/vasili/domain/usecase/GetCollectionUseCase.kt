package by.grodno.vasili.domain.usecase

import by.grodno.vasili.domain.model.Collection
import by.grodno.vasili.domain.repository.CollectionRepository

/**
 * Use case for retrieving [Collection] with page of items inside.
*/
class GetCollectionUseCase(
        private val repository: CollectionRepository
) : UseCase<GetCollectionUseCase.Params, Collection>() {

    /**
     * Parameters for request.
     */
    data class Params(val page: Int)

    override suspend fun action(params: Params): Collection {
        return repository.getCollection(params.page)
    }
}
