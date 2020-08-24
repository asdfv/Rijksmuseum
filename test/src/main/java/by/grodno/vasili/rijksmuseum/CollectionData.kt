package by.grodno.vasili.rijksmuseum

import by.grodno.vasili.domain.model.Details
import by.grodno.vasili.domain.model.PrincipalMaker
import by.grodno.vasili.domain.model.WebImage

fun details(objectNumber: String = "Test_objectNumber") = Details(
        id = "Test_id",
        objectNumber = objectNumber,
        title = "Test_title",
        webImage = webImage(),
        description = "Test_description",
        principalMaker = principalMaker()
)

fun webImage() = WebImage(
        guid = "Test_guid",
        width = 640,
        height = 480,
        url = "Test_url"
)

fun principalMaker() = PrincipalMaker(
        name = "Test_name",
        dateOfBirth = "Test_dateOfBirth",
        dateOfDeath = "Test_dateOfDeath"
)
