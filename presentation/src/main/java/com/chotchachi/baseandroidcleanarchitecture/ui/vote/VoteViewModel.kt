package com.chotchachi.baseandroidcleanarchitecture.ui.vote

import androidx.lifecycle.viewModelScope
import com.baseui.BaseViewModel
import com.domain.model.CatImage
import com.domain.repository.CatImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class VoteViewModel(
    catImageRepository: CatImageRepository
) : BaseViewModel() {
    private val _loadCatImagesTrigger = MutableSharedFlow<Unit>(extraBufferCapacity = 64)
    fun loadCatImages() = _loadCatImagesTrigger.tryEmit(Unit)

    val catImageData: StateFlow<List<CatImage>>

    init {
        catImageData = _loadCatImagesTrigger
            .flatMapLatest {
                catImageRepository.getCatImagesVoting()
            }.scan(listOf<CatImage>()) { accumulator, value ->
                accumulator + value
            }.stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                emptyList()
            )

        loadCatImages()
    }
}