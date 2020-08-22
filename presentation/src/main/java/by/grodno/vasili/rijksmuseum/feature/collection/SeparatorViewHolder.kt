package by.grodno.vasili.rijksmuseum.feature.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.grodno.vasili.rijksmuseum.R

/**
 * View holder class for separators with a first letter of art objects principal.
 */
class SeparatorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val description: TextView = view.findViewById(R.id.separator_description)

    /**
     * Bind object to its view.
     */
    fun bind(separatorText: String) {
        description.text = separatorText
    }

    companion object {
        /**
         * Create view holder instance with correct item view.
         */
        fun create(parent: ViewGroup): SeparatorViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.separator_view_item, parent, false)
            return SeparatorViewHolder(view)
        }
    }
}