package com.arum.movieinfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularResponse(

    val results: MutableList<PopularList>? = null
) : Parcelable

@Parcelize
data class PopularList(
    val id: Int,
    val poster_path: String?,
    val original_title: String?,
    val overview: String?,
    val backdrop_path: String

    ) : Parcelable
