package by.grodno.vasili.rijksmuseum.feature.collection

import android.content.Intent
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
import by.grodno.vasili.rijksmuseum.feature.details.DetailsActivity
import timber.log.Timber

/**
 * Activity represent list of items from collection.
 */
class CollectionActivity : BaseActivity<ActivityCollectionBinding>() {
    private lateinit var model: CollectionViewModel

    override val contentView = R.layout.activity_collection

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
