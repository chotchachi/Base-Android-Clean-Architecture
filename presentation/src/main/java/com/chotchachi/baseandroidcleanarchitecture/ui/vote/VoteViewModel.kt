package com.chotchachi.baseandroidcleanarchitecture.ui.vote

import androidx.lifecycle.viewModelScope
import com.baseui.BaseViewModel
import com.domain.model.CatImage
import com.domain.model.VoteResult
import com.domain.model.request.VoteRequest
import com.domain.repository.CatImageRepository
import com.domain.repository.VoteRepository
import com.utils.extensions.flow.withLatestFrom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class VoteViewModel(
    catImageRepository: CatImageRepository,
    voteRepository: VoteRepository
) : BaseViewModel() {
    val catImageData: StateFlow<List<CatImage>>

    private val _loadCatImagesTrigger = MutableStateFlow(Unit)
    fun loadCatImages() = _loadCatImagesTrigger.tryEmit(Unit)

    private val _voteUpTrigger = MutableSharedFlow<Int>(extraBufferCapacity = 64)
    fun voteUp(position: Int) = _voteUpTrigger.tryEmit(position)

    private val _voteDownTrigger = MutableSharedFlow<Int>(extraBufferCapacity = 64)
    fun voteDown(position: Int) = _voteDownTrigger.tryEmit(position)

    init {
        catImageData = _loadCatImagesTrigger
            .flatMapLatest {
                catImageRepository.getCatImagesVoting()
                    .catch { emit(emptyList()) }
            }
            .scan(listOf<CatImage>()) { accumulator, value ->
                accumulator + value
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                emptyList()
            )

        val voteUpTrigger = _voteUpTrigger
            .withLatestFrom(catImageData) { position, data ->
                data[position]
            }
            .map { VoteRequest(it.id, "ttquang", VoteRequest.VoteValue.UP) }

        val voteDownTrigger = _voteDownTrigger
            .withLatestFrom(catImageData) { position, data ->
                data[position]
            }
            .map { VoteRequest(it.id, "ttquang", VoteRequest.VoteValue.DOWN) }

        merge(voteUpTrigger, voteDownTrigger)
            .flatMapConcat { voteRequest ->
                voteRepository.sendVote(voteRequest)
                    .map { it as VoteResult? }
                    .catch { emit(null) }
            }
            .launchIn(viewModelScope)

    }
}