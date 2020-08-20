package by.grodno.vasili.data.response

import by.grodno.vasili.domain.model.PrincipalMaker
import com.google.gson.annotations.SerializedName

data class PrincipalMakersResponse(
		@SerializedName("name") val name: String,
		@SerializedName("dateOfBirth") val dateOfBirth: String,
		@SerializedName("dateOfDeath") val dateOfDeath: String?
) {

    fun toDomainModel() = PrincipalMaker(name, dateOfBirth, dateOfDeath)
}
