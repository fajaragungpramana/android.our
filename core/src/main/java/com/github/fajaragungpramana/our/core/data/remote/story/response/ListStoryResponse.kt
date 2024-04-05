package com.github.fajaragungpramana.our.core.data.remote.story.response

import com.google.gson.annotations.SerializedName

data class ListStoryResponse(

    @SerializedName("error")
    val boolean: Boolean? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("listStory")
    val listStory: List<StoryResponse>? = null

)