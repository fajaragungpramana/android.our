package com.github.fajaragungpramana.our.core.data.remote.story

import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.core.data.remote.story.response.ListStoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IStoryDataSource {

    @GET("/stories")
    suspend fun getListStory(@QueryMap getStoryRequest: GetStoryRequest): Response<ListStoryResponse>

}