package com.arum.movieinfo.model

import java.math.BigInteger

data class UserData(
    val avatar: AvatarPath,
    val id: BigInteger,
    val name: String,
    val include_adult: Boolean,
    val username: String
) {

    data class AvatarPath(
        val tmdb: TmdbPath
    ) {
        data class TmdbPath(
            val avatar_path: String
        )
    }
}
