package by.grodno.vasili.rijksmuseum.feature.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Basic class for activities.
 *
 * [VDB] databinding class.
 */
abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    /**
     * Access to UI bindings.
     */
    protected lateinit var binding: VDB

    /**
     * Content view for activity.
     */
    protected abstract val contentView: Int

    /**
     * Show toast message with [text].
     */
    protected fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        binding = DataBindingUtil.setContentView(this, contentView)
    }
}
