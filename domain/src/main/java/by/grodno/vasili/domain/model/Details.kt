package by.grodno.vasili.domain.model

data class Details(
        val id: String,
        val objectNumber: String,
        val title: String,
        val webImage: WebImage,
        val description: String,
        val principalMaker: PrincipalMaker
)
