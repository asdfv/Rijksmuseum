package by.grodno.vasili.domain.usecase

import by.grodno.vasili.domain.error.ErrorConverter
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.domain.repository.CollectionRepository

/**
 * Use case for retrieving [ArtObject]s from Collection.
 */
class GetCollectionUseCase(
        private val repository: CollectionRepository,
        errorConverter: ErrorConverter
) : UseCase<GetCollectionUseCase.Params, List<ArtObject>>(errorConverter) {

    /**
     * Parameters for request.
     */
    data class Params(val page: Int)

    override suspend fun action(params: Params): List<ArtObject> {
        return repository.getCollection(params.page).artObjects
    }
}
