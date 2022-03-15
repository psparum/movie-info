package com.arum.movieinfo.model

data class RequestTokenResponse(
    val success: Boolean?,
    val expires_at: String?,
    val request_token: String?
)
