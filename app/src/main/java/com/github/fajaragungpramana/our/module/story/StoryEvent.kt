package com.github.fajaragungpramana.our.module.story

import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest

sealed class StoryEvent {

    data class GetListStory(val getStoryRequest: GetStoryRequest) : StoryEvent()

}