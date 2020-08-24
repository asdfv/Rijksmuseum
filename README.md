## List/Details application for Rijksmuseum API

| WARNING: Do not forget to add your API key in `local.properties` file like this: ```api.key=<your key>```|
| --- |

### What is done
- Two screens (Collection and Details) application for Rijksmuseum API
- Used Clean Architecture Pattern with data/domain/presentation layers
- Loading indication
- List of ArtObjects is paginated
- List of ArtObjects is divided into sections by the first letter of principal maker
- Item in list contains image preview, artist and title
- Details screen load and present details about art object
- API key is taken from `local.properties` file
- Error handling with custom domain layers errors
- Written unit test for `DetailsViewModel`

###Tools are used
- Kotlin coroutines
- Android JetPack Pagination 3
- Android JetPack Hilt
- Android JetPack ViewModel/LiveData
- Android Databinding
- JUnit 5

### What can be improved
- Figure out with sorting on server side, because sorting by "artist" works strange, because in responce we did not have any "artist" field and "principalOrFirstMaker" are not sorted in response
- Response dos not have a small image for preview - it is a bad idea to download large images for preview in list items
- Check API documentation about nullable fields and change data response models with nullable types where needed
- Organize gradle dependencies versions in external module (example: [link](https://github.com/muratcanbur/ProjectX))
- Add a toolbar
- Add more tests, not only for the presentation layer
- Add JetPack Navigation
- Off-line handling and local cache
- Move base URL to build.gradle 
- Stop loading list of art objects when API data limit exceeded
