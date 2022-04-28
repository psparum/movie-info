package com.arum.movieinfo.network

import com.arum.movieinfo.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    fun postLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<UserData>

    @GET("movie/upcoming")
    fun getMovieUpcoming(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3"
    ): Call<UpcomingResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3"
    ): Call<NowPlayingResponse>

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3"
    ): Call<PopularResponse>

    @GET("movie/upcoming")
    fun getUpComing(
        @Query("api_key") api_key: String = "494caa05ffee149393c1cbf76a22d3f3"
    ): Call<UpcomingResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
    ): Call<DetailResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideos(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
    ): Call<VideoResponse>

}