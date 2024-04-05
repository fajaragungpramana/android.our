package com.github.fajaragungpramana.our.module.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.github.fajaragungpramana.our.common.app.AppPagingAdapter
import com.github.fajaragungpramana.our.common.app.AppRecyclerViewHolder
import com.github.fajaragungpramana.our.core.domain.story.model.Story
import com.github.fajaragungpramana.our.databinding.ItemStoryBinding

class StoryAdapter :
    AppPagingAdapter<ItemStoryBinding, Story, StoryAdapter.ViewHolder>(Story.diffUtil) {

    override fun onViewBinding(viewGroup: ViewGroup): ItemStoryBinding =
        ItemStoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

    override fun onViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    inner class ViewHolder(itemView: View) : AppRecyclerViewHolder<Story>(itemView) {

        override fun bindItem(item: Story, position: Int) {
            Log.d("FFFF", "HELLO $item")
            viewBinding.apply {
                mtvStoryName.text = item.name.orEmpty()
                aivStoryPhoto.load(item.photoUrl)
                mtvStoryDescription.text = item.description.orEmpty()
            }
        }

    }

}