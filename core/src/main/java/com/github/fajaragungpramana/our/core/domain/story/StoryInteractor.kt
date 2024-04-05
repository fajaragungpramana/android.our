package com.github.fajaragungpramana.our.core.domain.story

import androidx.paging.PagingData
import androidx.paging.map
import com.github.fajaragungpramana.our.core.data.remote.story.IStoryRepository
import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.core.domain.story.model.Story
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoryInteractor @Inject constructor(private val storyRepository: IStoryRepository) :
    StoryUseCase {

    override suspend fun getListStory(getStoryRequest: GetStoryRequest): Flow<PagingData<Story>> =
        channelFlow {
            storyRepository.getListStory(getStoryRequest).collectLatest {
                send(it.map { response -> Story.mapFromResponseToObject(response) })
            }
        }.flowOn(Dispatchers.IO)

}