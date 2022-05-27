package com.baseui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.baseui.binding.ViewBindingHolder
import com.baseui.binding.ViewBindingHolderImpl
import com.baseui.binding.getBinding
import timber.log.Timber

/**
 * Created by Thanh Quang on 21/05/2022.
 */
abstract class BaseFragment<ViewBindingType : ViewBinding> :
    Fragment(),
    ViewBindingHolder<ViewBindingType> by ViewBindingHolderImpl() {

    abstract val viewModel: BaseViewModel?

    private var _mContext: Context? = null

    val mContext: Context
        get() = _mContext
            ?: error("Accessing context was null")

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("$this::onAttach")
        _mContext = context
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = initBinding(
        binding = getBinding(inflater, container),
        lifecycle = viewLifecycleOwner.lifecycle,
        className = this::class.simpleName,
        onBound = null
    ).also { Timber.d("$this::onCreateView") }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("$this::onViewCreated")
        setupView(view, savedInstanceState)
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("$this::onDestroyView")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        Timber.d("$this::onDestroy")
    }

    @CallSuper
    override fun onDetach() {
        _mContext = null
        super.onDetach()
        Timber.d("$this::onDetach")
    }

    protected abstract fun setupView(view: View, savedInstanceState: Bundle?)
}
