package com.chotchachi.baseandroidcleanarchitecture.ui.vote

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.chotchachi.baseandroidcleanarchitecture.databinding.ItemCatImageCardBinding
import com.domain.model.CatImage

/**
 * Created by Thanh Quang on 23/07/2022.
 */
class CardStackAdapter(
    private val context: Context,
    private val itemClick: (CatImage) -> Unit
) : ListAdapter<CatImage, CardStackAdapter.CatImageCardVH>(DataDifferentiator) {
    private val inflater = LayoutInflater.from(context)

    inner class CatImageCardVH(private val itemBinding: ItemCatImageCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private var circularProgressDrawable = CircularProgressDrawable(context)

        init {
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
        }

        fun bind(item: CatImage) {
            itemBinding.root.setOnClickListener { itemClick(item) }
            itemBinding.ivCatThumb.load(item.url) {
                placeholder(circularProgressDrawable)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatImageCardVH(
        ItemCatImageCardBinding.inflate(inflater, parent, false)
    )

    override fun onBindViewHolder(holder: CatImageCardVH, position: Int) =
        holder.bind(getItem(position))

    object DataDifferentiator : DiffUtil.ItemCallback<CatImage>() {
        override fun areItemsTheSame(oldItem: CatImage, newItem: CatImage) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CatImage, newItem: CatImage) = oldItem == newItem
    }
}