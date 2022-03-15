package com.arum.movieinfo.model

data class LoginDataResponse(
    val success: Boolean?,
    val expires_at: String?,
    val request_token: String?
)
