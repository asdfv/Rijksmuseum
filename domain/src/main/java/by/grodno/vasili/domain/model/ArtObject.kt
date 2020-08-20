package by.grodno.vasili.domain.model

data class ArtObject(
	val links: Links,
	val id: String,
	val objectNumber: String,
	val title: String,
	val hasImage: Boolean,
	val principalOrFirstMaker: String,
	val longTitle: String,
	val webImage: WebImage
)
