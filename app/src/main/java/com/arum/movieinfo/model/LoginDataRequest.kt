package com.arum.movieinfo.model

data class LoginDataRequest(
    val username: String,
    val password: String,
    val request_token: String
)
