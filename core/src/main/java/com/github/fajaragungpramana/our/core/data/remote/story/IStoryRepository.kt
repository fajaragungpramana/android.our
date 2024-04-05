package com.github.fajaragungpramana.our.core.data.remote.story

import androidx.paging.PagingData
import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.core.data.remote.story.response.StoryResponse
import kotlinx.coroutines.flow.Flow

interface IStoryRepository {

    suspend fun getListStory(getStoryRequest: GetStoryRequest): Flow<PagingData<StoryResponse>>

}