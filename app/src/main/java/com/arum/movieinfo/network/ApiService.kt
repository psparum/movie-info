package com.arum.movieinfo.network

import com.arum.movieinfo.model.*
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    fun postLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<UserData>

}