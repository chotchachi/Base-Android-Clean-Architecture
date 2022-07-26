package com.chotchachi.baseandroidcleanarchitecture.ui.breed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chotchachi.baseandroidcleanarchitecture.databinding.ItemCatBreedBinding
import com.domain.model.Breed

/**
 * Created by Thanh Quang on 15/07/2022.
 */
class BreedAdapter(
    context: Context,
    private val itemClick: (Breed) -> Unit
) : PagingDataAdapter<Breed, BreedAdapter.BreedVH>(DataDifferentiator) {
    private val inflater = LayoutInflater.from(context)

    inner class BreedVH(private val itemBinding: ItemCatBreedBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Breed) {
            itemBinding.root.setOnClickListener { itemClick(item) }
            itemBinding.tvBreedName.text = item.name
            itemBinding.tvBreedDescription.text = item.description
            itemBinding.ivBreedThumb.load(item.image?.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BreedVH(
        ItemCatBreedBinding.inflate(
            inflater,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BreedVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    object DataDifferentiator : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed) = oldItem == newItem
    }
}