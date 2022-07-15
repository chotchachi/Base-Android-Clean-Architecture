package com.chotchachi.baseandroidcleanarchitecture.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.baseui.BaseFragment
import com.bumptech.glide.Glide
import com.chotchachi.baseandroidcleanarchitecture.databinding.FragmentHomeBinding
import com.domain.model.Breed
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel by viewModel<HomeViewModel>()

    private val breedAdapter by lazy {
        BreedAdapter(mContext, Glide.with(this), ::handleBreedItemClick)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        breedAdapter.addLoadStateListener {
//            retryButton.isVisible = it.refresh is LoadState.Error
            binding.swipeRefreshLayout.isRefreshing = it.refresh is LoadState.Loading
//            emptyState.isVisible = it.refresh is LoadState.Loading && adapter.itemCount == 0
        }

        binding.rvBreeds.run {
            setHasFixedSize(true)
            adapter = breedAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener { breedAdapter.refresh() }
    }

    override fun bindViewModel() {
        viewModel.catBreedData
            .onEach { breedAdapter.submitData(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleBreedItemClick(breed: Breed) {
        TODO("Not yet implemented")
    }
}