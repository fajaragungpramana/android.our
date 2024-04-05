package com.github.fajaragungpramana.our.module.story

import androidx.paging.PagingData
import com.github.fajaragungpramana.our.core.domain.story.model.Story

sealed class StoryState {

    data class OnStoryData(val pagingData: PagingData<Story>) : StoryState()

}