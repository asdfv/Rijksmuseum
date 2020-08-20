package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.Links
import com.google.gson.annotations.SerializedName

/**
 * Data model for Links response.
 */
data class LinksResponse(
		@SerializedName("self") val self: String,
		@SerializedName("web") val web: String
) {

    fun toDomainModel() = Links(self, web)
}
