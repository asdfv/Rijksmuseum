package by.grodno.vasili.rijksmuseum.feature.collection

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.grodno.vasili.data.datasource.retrofit.RetrofitCollectionDatasource
import by.grodno.vasili.data.error.DataErrorConverter
import by.grodno.vasili.data.repository.CollectionDataRepository
import by.grodno.vasili.domain.usecase.GetCollectionUseCase
import by.grodno.vasili.rijksmuseum.BuildConfig
import by.grodno.vasili.rijksmuseum.R
import by.grodno.vasili.rijksmuseum.databinding.ActivityCollectionBinding
import by.grodno.vasili.rijksmuseum.feature.base.BaseActivity
import by.grodno.vasili.rijksmuseum.feature.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Activity represent list of items from collection.
 */
@AndroidEntryPoint
class CollectionActivity : BaseActivity<ActivityCollectionBinding>() {

    private val model: CollectionViewModel by viewModels {
        val getCollectionUseCase = GetCollectionUseCase(
                CollectionDataRepository(RetrofitCollectionDatasource(BuildConfig.API_KEY)),
                DataErrorConverter())
        CollectionViewModelFactory(getCollectionUseCase)
    }

    override val contentView = R.layout.activity_collection

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = CollectionAdapter()
        observeLoadingStates(adapter, binding.progressBar)
        initRecyclerView(this, binding.recyclerView, adapter)
        initPullToRefresh(model, binding.refreshContainer)
        lifecycleScope.launch { initPagination(model, adapter) }
    }

    private fun observeLoadingStates(adapter: CollectionAdapter, progressBar: ProgressBar) {
        adapter.addLoadStateListener {
            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest { loadStates ->
                    progressBar.isVisible = loadStates.refresh is LoadState.Loading
                    if (loadStates.refresh is LoadState.Error) showToast(getString(R.string.error_loading_collection))
                }
            }
        }
    }

    private suspend fun initPagination(model: CollectionViewModel, adapter: CollectionAdapter) {
        model.artObjectsFlow.collectLatest {
            binding.refreshContainer.isRefreshing = false
            adapter.submitData(it)
        }
    }

    /**
     * Handler for click on recycler view item.
     */
    fun onItemClick(objectNumber: String?) {
        if (objectNumber == null) {
            showToast("Sorry, we can`t show you this item details.")
            Timber.e("Empty id in recycler view.")
            return
        }
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.OBJECT_NUMBER_KEY, objectNumber)
        startActivity(intent)
    }

    private fun initRecyclerView(
            context: CollectionActivity,
            recyclerView: RecyclerView,
            collectionAdapter: CollectionAdapter
    ) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = collectionAdapter
        }
    }

    private fun initPullToRefresh(model: CollectionViewModel, refreshContainer: SwipeRefreshLayout) {
        refreshContainer.setOnRefreshListener { model.invalidateDatasource() }
    }
}
