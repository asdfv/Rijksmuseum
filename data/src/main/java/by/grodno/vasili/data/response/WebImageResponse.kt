package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.WebImage
import com.google.gson.annotations.SerializedName

/**
 * Data model for WebImage response.
 */
data class WebImageResponse(
        @SerializedName("guid") val guid: String,
        @SerializedName("width") val width: Int,
        @SerializedName("height") val height: Int,
        @SerializedName("url") val url: String
) {
    /**
     * Converter to domain model.
     */
    fun toDomainModel() = WebImage(guid, width, height, url)
}
