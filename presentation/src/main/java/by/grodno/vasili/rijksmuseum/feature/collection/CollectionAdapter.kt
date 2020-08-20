package by.grodno.vasili.rijksmuseum.feature.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.rijksmuseum.R
import by.grodno.vasili.rijksmuseum.databinding.ArtobjectItemBinding

/**
 * Adapter for paginated recycler view with collection's items.
 */
class CollectionAdapter(
    diffUtilCallback: DiffUtil.ItemCallback<ArtObject>
) : PagedListAdapter<ArtObject, CollectionAdapter.ViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.artobject_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding?.apply {
            artObject = item
            handler = holder.itemView.context as CollectionActivity
        }
    }

    /**
     * ViewHolder class for items Recycler view.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ArtobjectItemBinding? = DataBindingUtil.bind(itemView)
    }
}