package com.chotchachi.baseandroidcleanarchitecture.ui.breed

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.baseui.BaseViewModel
import com.domain.repository.BreedRepository

class BreedViewModel(
    breedRepository: BreedRepository
) : BaseViewModel() {

    val catBreedData = breedRepository
        .getBreed()
        .cachedIn(viewModelScope)
}