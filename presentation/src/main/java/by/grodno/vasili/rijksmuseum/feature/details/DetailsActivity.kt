package by.grodno.vasili.rijksmuseum.feature.details

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.grodno.vasili.rijksmuseum.R
import by.grodno.vasili.rijksmuseum.databinding.ActivityDetailsBinding
import by.grodno.vasili.rijksmuseum.feature.base.BaseActivity

class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {
    companion object {
        const val OBJECT_NUMBER_KEY = "by.grodno.vasili.rijksmuseum.feature.details.EXTRA.OBJECT_NUMBER_KEY"
    }

    private lateinit var model: DetailsViewModel

    override val contentView = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dependency = DetailsDependenciesModule()
        model = ViewModelProvider(this, dependency.factory).get(DetailsViewModel::class.java)
        // todo !! error handling
        val objectNumber = intent.getStringExtra(OBJECT_NUMBER_KEY)
        model.loadDetails(objectNumber).observe(this) {
            binding.artObjectDetails = it
        }
    }
}
