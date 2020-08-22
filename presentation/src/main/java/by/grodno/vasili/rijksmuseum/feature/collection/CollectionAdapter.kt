package by.grodno.vasili.rijksmuseum.feature.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
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
) : PagingDataAdapter<ArtObject, CollectionAdapter.ArtObjectViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtObjectViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.artobject_item, parent, false)
        return ArtObjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArtObjectViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding?.apply {
                artObject = item
                handler = holder.itemView.context as CollectionActivity
            }
        }
    }

    /**
     * ViewHolder class for items Recycler view.
     */
    class ArtObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ArtobjectItemBinding? = DataBindingUtil.bind(itemView)
    }
}
