package com.github.fajaragungpramana.our.core.domain.story

import androidx.paging.PagingData
import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.core.domain.story.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryUseCase {

    suspend fun getListStory(getStoryRequest: GetStoryRequest): Flow<PagingData<Story>>

}