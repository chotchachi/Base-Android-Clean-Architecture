package com.chotchachi.baseandroidcleanarchitecture.ui.vote

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import com.baseui.BaseFragment
import com.bumptech.glide.Glide
import com.chotchachi.baseandroidcleanarchitecture.databinding.FragmentVoteBinding
import com.chotchachi.baseandroidcleanarchitecture.view.cardstackview.*
import com.domain.model.CatImage
import com.utils.extensions.collectInViewLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class VoteFragment : BaseFragment<FragmentVoteBinding>(), CardStackListener {
    override val viewModel by viewModel<VoteViewModel>()

    private lateinit var cardStackLayoutManager: CardStackLayoutManager
    private val cardStackAdapter by lazy {
        CardStackAdapter(
            mContext,
            Glide.with(this),
            ::handleCardItemClick
        )
    }
    private var currentCardPosition: Int? = null

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        cardStackLayoutManager = CardStackLayoutManager(
            mContext,
            this
        )
        cardStackLayoutManager.setStackFrom(StackFrom.None)
        cardStackLayoutManager.setVisibleCount(3)
        cardStackLayoutManager.setTranslationInterval(8.0f)
        cardStackLayoutManager.setScaleInterval(0.95f)
        cardStackLayoutManager.setSwipeThreshold(0.3f)
        cardStackLayoutManager.setMaxDegree(20.0f)
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL)
        cardStackLayoutManager.setCanScrollHorizontal(true)
        cardStackLayoutManager.setCanScrollVertical(true)
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        cardStackLayoutManager.setOverlayInterpolator(LinearInterpolator())
        binding.cardStackView.run {
            layoutManager = cardStackLayoutManager
            adapter = cardStackAdapter
            itemAnimator.apply {
                if (this is DefaultItemAnimator) {
                    supportsChangeAnimations = false
                }
            }
        }

        binding.btnVoteDown.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackLayoutManager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }

        binding.btnRewind.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            cardStackLayoutManager.setRewindAnimationSetting(setting)
            binding.cardStackView.rewind()
        }

        binding.btnVoteUp.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackLayoutManager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }
    }

    override fun bindViewModel() {
        viewModel.catImageData.collectInViewLifecycle(this, action = ::handleCatImageData)
    }

    private fun handleCatImageData(items: List<CatImage>) {
        cardStackAdapter.submitList(items)
        currentCardPosition?.let { binding.cardStackView.scrollToPosition(it) }
    }

    private fun handleCardItemClick(catImage: CatImage) {

    }

    /**
     * [CardStackListener]
     * */

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Timber.d("onCardDragging: direction = ${direction?.name}, ratio = $ratio")
    }

    override fun onCardSwiped(direction: Direction?) {
        Timber.d("onCardSwiped: position = ${cardStackLayoutManager.topPosition}, direction = $direction")
        currentCardPosition = cardStackLayoutManager.topPosition
        if (cardStackLayoutManager.topPosition == cardStackAdapter.itemCount - 5) {
            // Load more
            Timber.d("onCardSwiped: load more data")
            viewModel.loadCatImages()
        }
    }

    override fun onCardRewound() {
        Timber.d("onCardRewound: ${cardStackLayoutManager.topPosition}")
    }

    override fun onCardCanceled() {
        Timber.d("onCardCanceled: ${cardStackLayoutManager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        Timber.d("onCardAppeared: position = $position")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        Timber.d("onCardDisappeared: position = $position")
    }

}