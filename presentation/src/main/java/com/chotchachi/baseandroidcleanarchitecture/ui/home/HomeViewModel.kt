package com.chotchachi.baseandroidcleanarchitecture.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.baseui.BaseViewModel
import com.domain.repository.BreedRepository

class HomeViewModel(
    breedRepository: BreedRepository
) : BaseViewModel() {

    val catBreedData = breedRepository
        .getBreed()
        .cachedIn(viewModelScope)
}