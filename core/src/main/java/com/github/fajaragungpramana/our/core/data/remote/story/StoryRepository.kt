package com.github.fajaragungpramana.our.core.data.remote.story

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.core.data.remote.story.response.StoryResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoryRepository @Inject constructor(private val storyDataSource: IStoryDataSource) :
    IStoryRepository {

    override suspend fun getListStory(getStoryRequest: GetStoryRequest): Flow<PagingData<StoryResponse>> =
        Pager(PagingConfig(pageSize = getStoryRequest.perPage ?: 15)) {
            StoryPagingSource(storyDataSource, getStoryRequest)
        }.flow

}