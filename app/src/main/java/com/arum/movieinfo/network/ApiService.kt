package com.arum.bottomnavigation.network

import com.arum.movieinfo.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("authentication/token/new")
    fun getRequestToken(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3"
    ): Call<RequestTokenResponse>

    @POST("authentication/token/validate_with_login")
    fun postLoginData(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3",
        @Body request: LoginDataRequest
    ): Call<LoginDataResponse>

    @POST("authentication/session/new")
    fun getSessionData(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3",
        @Body request: GetSessionRequest
    ): Call<GetSessionResponse>

    @GET("account")
    fun getUserData(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3",
        @Query("session_id") session_id: String
    ): Call<UserData>

}