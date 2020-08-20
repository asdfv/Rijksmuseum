package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.Collection
import com.google.gson.annotations.SerializedName

/**
 * Data model for Collection response.
 */
data class CollectionResponse(
		@SerializedName("count") val count: Int,
		@SerializedName("artObjects") val artObjects: List<ArtObjectResponse>
) {

    fun toDomainModel() = Collection(count, artObjects.map { it.toDomainModel() })
}