package by.grodno.vasili.rijksmuseum.feature.collection

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.grodno.vasili.rijksmuseum.R
import by.grodno.vasili.rijksmuseum.databinding.ActivityCollectionBinding
import by.grodno.vasili.rijksmuseum.feature.base.BaseActivity
import by.grodno.vasili.rijksmuseum.feature.details.DetailsActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Activity represent list of items from collection.
 */
class CollectionActivity : BaseActivity<ActivityCollectionBinding>() {
    private lateinit var model: CollectionViewModel

    override val contentView = R.layout.activity_collection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dependencies = CollectionDependenciesModule()
        model = ViewModelProvider(this, dependencies.factory).get(CollectionViewModel::class.java)
        val adapter = dependencies.adapter
        initRecyclerView(this, binding.recyclerView, adapter)
        initPullToRefresh(model, binding.refreshContainer)
        lifecycleScope.launch { initPagination(model, adapter) }
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
