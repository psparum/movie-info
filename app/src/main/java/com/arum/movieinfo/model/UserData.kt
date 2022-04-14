package com.arum.movieinfo.model

data class UserData(
    val createdAt: String,
    val id: String,
    val username: String,
    val profile: Profile
) {

    data class Profile(
        val firstName: String,
        val lastName: String,
        val image_url: String
    )
}
