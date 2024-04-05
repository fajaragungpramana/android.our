package com.github.fajaragungpramana.our.core.domain.story.model

import androidx.recyclerview.widget.DiffUtil
import com.github.fajaragungpramana.our.core.data.remote.story.response.StoryResponse

data class Story(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var photoUrl: String? = null,
    var createdAt: String? = null
) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Story>() {

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean =
                oldItem == newItem

        }

        fun mapFromResponseToObject(storyResponse: StoryResponse): Story = Story(
            id = storyResponse.id,
            name = storyResponse.name,
            description = storyResponse.description,
            photoUrl = storyResponse.photoUrl,
            createdAt = storyResponse.createdAt
        )

    }

}