package com.chotchachi.baseandroidcleanarchitecture.ui.breed

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.baseui.BaseFragment
import com.chotchachi.baseandroidcleanarchitecture.databinding.FragmentBreedBinding
import com.domain.model.Breed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class BreedFragment : BaseFragment<FragmentBreedBinding>() {
    override val viewModel by viewModel<BreedViewModel>()

    private val breedAdapter by lazy {
        BreedAdapter(mContext, ::handleBreedItemClick)
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

        lifecycleScope.launchWhenCreated {
            breedAdapter.loadStateFlow.collectLatest {

            }
        }
    }

    private fun handleBreedItemClick(breed: Breed) {
        TODO("Not yet implemented")
    }
}