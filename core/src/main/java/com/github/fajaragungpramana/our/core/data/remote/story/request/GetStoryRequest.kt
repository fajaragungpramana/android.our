package com.github.fajaragungpramana.our.core.data.remote.story.request

import com.github.fajaragungpramana.our.core.extension.removeNulls

data class GetStoryRequest(
    var page: Int? = null,
    var perPage: Int? = null,
    var location: Int? = null
) : Map<String, Any> by mapOf(
    "page" to page,
    "size" to perPage,
    "location" to location
).removeNulls()