package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.ArtObject
import com.google.gson.annotations.SerializedName

/**
 * Data model for ArtObject response.
 */
data class ArtObjectResponse(
	@SerializedName("links") val links: LinksResponse,
	@SerializedName("id") val id: String,
	@SerializedName("objectNumber") val objectNumber: String,
	@SerializedName("title") val title: String,
	@SerializedName("hasImage") val hasImage: Boolean,
	@SerializedName("principalOrFirstMaker") val principalOrFirstMaker: String,
	@SerializedName("longTitle") val longTitle: String,
	@SerializedName("webImage") val webImage: WebImageResponse
) {
	/**
	 * Converter to domain model.
	 */
    fun toDomainModel() = ArtObject(
		links = links.toDomainModel(),
		id = id,
		objectNumber = objectNumber,
		title = title,
		hasImage = hasImage,
		principalOrFirstMaker = principalOrFirstMaker,
		longTitle = longTitle,
		webImage = webImage.toDomainModel()
	)
}
