package com.chotchachi.baseandroidcleanarchitecture.ui.vote

import android.os.Bundle
import android.view.View
import com.baseui.BaseFragment
import com.chotchachi.baseandroidcleanarchitecture.databinding.FragmentVoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VoteFragment : BaseFragment<FragmentVoteBinding>() {
    override val viewModel by viewModel<VoteViewModel>()

    override fun setupView(view: View, savedInstanceState: Bundle?) {

    }

    override fun bindViewModel() {

    }
}