package by.grodno.vasili.rijksmuseum.feature.collection

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.rijksmuseum.R
import by.grodno.vasili.rijksmuseum.databinding.ActivityCollectionBinding
import by.grodno.vasili.rijksmuseum.feature.base.BaseActivity
import timber.log.Timber

/**
 * Activity represent list of items from collection.
 */
class CollectionActivity : BaseActivity<ActivityCollectionBinding>() {
    private lateinit var model: CollectionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dependencies = CollectionDependenciesModule(lifecycleScope)
        val adapter = dependencies.adapter
        model = ViewModelProvider(this, dependencies.factory).get(CollectionViewModel::class.java)
        initRecyclerView(adapter)
        initPullToRefresh()
        model.pagedListLiveData.observe(this, stopRefreshAndSubmitPageList(adapter))
    }

    /**
     * Handler for click on recycler view item.
     */
    fun onItemClick(id: String?) {
        if (id == null) {
            showToast("Sorry, we can`t show you this item details.")
            Timber.e("Empty id in recycler view.")
            return
        }
        showToast(id)
    }

    override val contentView = R.layout.activity_collection

    private fun initRecyclerView(adapter: CollectionAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun initPullToRefresh() {
        binding.refreshContainer.setOnRefreshListener { model.invalidateDatasource() }
    }

    private fun stopRefreshAndSubmitPageList(adapter: CollectionAdapter): Observer<PagedList<ArtObject>> {
        return Observer { pagedList: PagedList<ArtObject> ->
            binding.refreshContainer.isRefreshing = false
            adapter.submitList(pagedList)
        }
    }
}
