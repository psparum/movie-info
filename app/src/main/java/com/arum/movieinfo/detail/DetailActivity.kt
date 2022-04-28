package com.arum.movieinfo.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arum.bottomnavigation.network.NetworkConfig
import com.arum.movieinfo.databinding.ActivityDetailBinding
import com.arum.movieinfo.model.DetailResponse
import com.arum.movieinfo.model.VideoResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private var idMovie: Int? = null
    private var videoUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idMovie = intent.getIntExtra("idMovie", 0)
        getMovieDetail()
        getVideos()

        binding.iToolbar.ivBack.setOnClickListener {
            finish()
        }

    }

    private fun getMovieDetail() {
        NetworkConfig().getService()
            .getDetailMovie(
                movie_id = idMovie.toString(),
                api_key = "494caa05ffee149393c1cbf76a22d3f3"
            )
            .enqueue(object : Callback<DetailResponse> {

                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    response.body()?.let { setView(it) }

                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {

                }
            })
    }

    private fun getVideos() {
        NetworkConfig().getService()
            .getVideos(movie_id = idMovie.toString(), api_key = "494caa05ffee149393c1cbf76a22d3f3")
            .enqueue(object : Callback<VideoResponse> {

                override fun onResponse(
                    call: Call<VideoResponse>,
                    response: Response<VideoResponse>
                ) {
                    response.body()?.let {
                        val trailer = it.results.filter {
                            it.type == "Trailer"
                        }.first()
                        videoUrl = "https://www.youtube.com/watch?v=" + trailer.key
                    }
                    binding.ivPlay.setOnClickListener {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(videoUrl)
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<VideoResponse>, t: Throwable) {

                }
            })
    }

    private fun setView(data: DetailResponse) {
        binding.tvTitle.text = data.original_title
        binding.tvDateRelease.text = data.release_date
        binding.tvIsiDesc.text = data.overview
        var genres = ""
        data?.genres.forEach {
            genres += it.name + ", "
        }
        tvGenre.text = "Genres : " + genres.dropLast(2)


        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w780/" + data.backdrop_path)
            .into(binding.ivDetail)

        //"https://image.tmdb.org/t/p/w780/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    }
}