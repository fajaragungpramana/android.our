package com.github.fajaragungpramana.our.core.data.remote.story

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.fajaragungpramana.our.core.data.remote.story.request.GetStoryRequest
import com.github.fajaragungpramana.our.core.data.remote.story.response.StoryResponse
import javax.inject.Inject

class StoryPagingSource @Inject constructor(
    private val storyDataSource: IStoryDataSource,
    private val getStoryRequest: GetStoryRequest
) : PagingSource<Int, StoryResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoryResponse> = try {
        val page = params.key ?: 1

        val listStoryResponse = storyDataSource.getListStory(
            getStoryRequest.copy(page = 1)
        ).body()?.listStory.orEmpty()
        LoadResult.Page(
            data = listStoryResponse,
            prevKey = if (page == 1) null else page,
            nextKey = if (listStoryResponse.isEmpty()) null else page + 1
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, StoryResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

}