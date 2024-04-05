package com.github.fajaragungpramana.our.core.domain.story.model

import com.github.fajaragungpramana.our.core.data.remote.story.response.StoryResponse

data class Story(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var photoUrl: String? = null,
    var createdAt: String? = null
) {

    companion object {

        fun mapFromResponseToObject(storyResponse: StoryResponse): Story = Story(
            id = storyResponse.id,
            name = storyResponse.name,
            description = storyResponse.description,
            photoUrl = storyResponse.photoUrl,
            createdAt = storyResponse.createdAt
        )

    }

}