package com.baseui.binding

import android.view.View
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding

/**
 * Created by Thanh Quang on 21/05/2022.
 */
interface ViewBindingHolder<ViewBindingType : ViewBinding> {
    fun initBinding(
        binding: ViewBindingType,
        lifecycle: Lifecycle,
        className: String?,
        onBound: (ViewBindingType.() -> Unit)?
    ): View

    fun requireBinding(block: (ViewBindingType.() -> Unit)? = null): ViewBindingType
}

class ViewBindingHolderImpl<ViewBindingType : ViewBinding> :
    ViewBindingHolder<ViewBindingType>,
    DefaultLifecycleObserver {
    private var _binding: ViewBindingType? = null
    private var lifecycle: Lifecycle? = null

    private lateinit var className: String

    val binding: ViewBindingType
        get() = _binding
            ?: error("Accessing binding outside lifecycle: $className")

    override fun requireBinding(block: (ViewBindingType.() -> Unit)?) =
        binding.apply { block?.invoke(this) }

    override fun initBinding(
        binding: ViewBindingType,
        lifecycle: Lifecycle,
        className: String?,
        onBound: (ViewBindingType.() -> Unit)?
    ): View {
        this._binding = binding
        this.lifecycle = lifecycle
        this.lifecycle?.addObserver(this)
        this.className = className ?: "N/A"
        onBound?.invoke(binding)
        return binding.root
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycle?.removeObserver(this)
        lifecycle = null
        _binding = null
    }
}
