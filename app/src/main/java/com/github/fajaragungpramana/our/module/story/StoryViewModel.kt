package com.github.fajaragungpramana.our.module.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.core.domain.story.StoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val storyUseCase: StoryUseCase) : ViewModel() {

    private val _state by lazy { Channel<StoryState>() }
    val state: Flow<StoryState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: StoryEvent): Job = viewModelScope.launch {
        when (event) {
            is StoryEvent.GetListStory -> getListStory(event.getStoryRequest)
        }
    }

    private fun getListStory(getStoryRequest: GetStoryRequest): Job = viewModelScope.launch {
        storyUseCase.getListStory(getStoryRequest).collectLatest {
            _state.send(StoryState.OnStoryData(it))
        }
    }

}