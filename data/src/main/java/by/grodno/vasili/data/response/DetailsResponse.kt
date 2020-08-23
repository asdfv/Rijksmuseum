package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.Details
import com.google.gson.annotations.SerializedName

data class DetailsResponse(
        @SerializedName("artObject") val artObjectDetails: ArtObjectDetailsResponse
) {

    fun toDomainModel() = Details(
            id = artObjectDetails.id,
            objectNumber = artObjectDetails.objectNumber,
            title = artObjectDetails.title,
            webImage = artObjectDetails.webImage.toDomainModel(),
            description = artObjectDetails.description ?: "",
            principalMaker = artObjectDetails.principalMakerResponses.map { it.toDomainModel() }.first()
    )
}
