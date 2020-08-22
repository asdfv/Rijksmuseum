package by.grodno.vasili.rijksmuseum.feature.collection

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.grodno.vasili.rijksmuseum.R

/**
 * Adapter for paginated recycler view with collection's items.
 */
class CollectionAdapter : PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(UIMODEL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.artobject_item) {
            ArtObjectViewHolder.create(parent)
        } else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position) ?: throw UnsupportedOperationException("It is a placeholder.")
        return when (item) {
            is UiModel.ArtObjectItem -> R.layout.artobject_item
            is UiModel.SeparatorItem -> R.layout.separator_view_item
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position) ?: return
        when (uiModel) {
            is UiModel.ArtObjectItem -> (holder as ArtObjectViewHolder).bind(uiModel.artObject)
            is UiModel.SeparatorItem -> (holder as SeparatorViewHolder).bind(uiModel.description)
        }
    }

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (
                        oldItem is UiModel.ArtObjectItem
                                && newItem is UiModel.ArtObjectItem
                                && oldItem.artObject.id == newItem.artObject.id
                        )
                        || (
                        oldItem is UiModel.SeparatorItem
                                && newItem is UiModel.SeparatorItem
                                && oldItem.description == newItem.description
                        )
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                    oldItem == newItem
        }
    }
}
