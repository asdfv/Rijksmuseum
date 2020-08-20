package by.grodno.vasili.data.response

import com.google.gson.annotations.SerializedName

/**
 * Data model for art object details.
 */
data class ArtObjectDetailsResponse(
		@SerializedName("id") val id: String,
		@SerializedName("objectNumber") val objectNumber: String,
		@SerializedName("title") val title: String,
		@SerializedName("webImage") val webImage: WebImageResponse,
		@SerializedName("description") val description: String,
		@SerializedName("principalMakers") val principalMakerResponses: List<PrincipalMakersResponse>
)
