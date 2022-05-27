package com.baseui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.baseui.binding.ViewBindingHolder
import com.baseui.binding.ViewBindingHolderImpl
import com.baseui.binding.getBinding
import timber.log.Timber

/**
 * Created by Thanh Quang on 21/05/2022.
 */
abstract class BaseActivity<ViewBindingType : ViewBinding> :
    AppCompatActivity(),
    ViewBindingHolder<ViewBindingType> by ViewBindingHolderImpl() {

    abstract val viewModel: BaseViewModel?

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            initBinding(
                binding = getBinding(),
                lifecycle = lifecycle,
                className = this::class.simpleName,
                onBound = null
            )
        ).also { Timber.d("$this::onCreate")  }
        setupView(savedInstanceState)
    }

    protected abstract fun setupView(savedInstanceState: Bundle?)
}
