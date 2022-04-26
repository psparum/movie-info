package com.arum.movieinfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpcomingResponse(val results: MutableList<UpcomingList>) : Parcelable

@Parcelize
data class UpcomingList(
    val id: Int?,
    val poster_path: String?,
    val original_title: String?,
    val title: String?,
    val release_date: String?,
    val vote_average: Double?,
    val backdrop_path: String?,
    val overview: String?

) : Parcelable