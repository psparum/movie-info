package com.arum.movieinfo.model

data class VideoResponse(

    val results: MutableList<VideoList>
){
    data class VideoList(
        val key: String,
        val type: String
    )
}
