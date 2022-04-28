package com.arum.movieinfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NowPlayingResponse(
    val results: MutableList<NowPlayingList>? = null

) : Parcelable

@Parcelize
data class NowPlayingList(
    val id: Int,
    val original_title: String? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val vote_average: Double? = null,
    val overview: String,
    val backdrop_path: String
) : Parcelable
