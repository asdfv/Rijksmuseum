package by.grodno.vasili.rijksmuseum.feature.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import by.grodno.vasili.rijksmuseum.R
import com.bumptech.glide.Glide

/**
 * Class for bindings utils and adapters.
 */
object BindingUtil {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (url == null) {
            Glide.with(imageView.context).load(R.drawable.placeholder_image).into(imageView);
        } else {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}
