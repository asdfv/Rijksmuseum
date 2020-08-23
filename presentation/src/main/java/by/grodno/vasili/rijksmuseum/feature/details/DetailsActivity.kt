package by.grodno.vasili.rijksmuseum.feature.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import by.grodno.vasili.domain.usecase.Result
import by.grodno.vasili.rijksmuseum.R
import by.grodno.vasili.rijksmuseum.databinding.ActivityDetailsBinding
import by.grodno.vasili.rijksmuseum.feature.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {
    companion object {
        const val OBJECT_NUMBER_KEY = "by.grodno.vasili.rijksmuseum.feature.details.EXTRA.OBJECT_NUMBER_KEY"
    }

    private val model: DetailsViewModel by viewModels()

    override val contentView = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val objectNumber = intent.getStringExtra(OBJECT_NUMBER_KEY)
        observeDetails(objectNumber, model, this)
    }

    private fun observeDetails(
            objectNumber: String?,
            model: DetailsViewModel,
            owner: LifecycleOwner
    ) {
        model.loadDetails(objectNumber).observe(owner) { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    binding.artObjectDetails = result.data
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    showToast("Loading problem.")
                    Timber.e(result.exception, "Error loading art object $objectNumber.")
                }
            }
        }
    }
}
