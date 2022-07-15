package com.chotchachi.baseandroidcleanarchitecture.ui.home

import android.os.Bundle
import android.view.View
import com.baseui.BaseFragment
import com.chotchachi.baseandroidcleanarchitecture.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel by viewModel<HomeViewModel>()

    override fun setupView(view: View, savedInstanceState: Bundle?) {

    }
}