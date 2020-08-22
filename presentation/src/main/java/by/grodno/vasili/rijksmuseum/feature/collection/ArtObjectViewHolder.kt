package by.grodno.vasili.rijksmuseum.feature.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.grodno.vasili.domain.model.ArtObject
import by.grodno.vasili.rijksmuseum.R
import by.grodno.vasili.rijksmuseum.databinding.ArtobjectItemBinding

/**
 * ViewHolder class for art object.
 */
class ArtObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Bind object to its view.
     */
    fun bind(artObject: ArtObject) {
        val binding: ArtobjectItemBinding? = DataBindingUtil.bind(itemView)
        binding?.artObject = artObject
        binding?.handler = itemView.context as CollectionActivity
    }

    /**
     * Create view holder instance with correct item view.
     */
    companion object {
        fun create(parent: ViewGroup): ArtObjectViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.artobject_item, parent, false)
            return ArtObjectViewHolder(view)
        }
    }
}
