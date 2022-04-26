package com.arum.movieinfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arum.bottomnavigation.network.NetworkConfig
import com.arum.movieinfo.R
import com.arum.movieinfo.adapter.*
import com.arum.movieinfo.databinding.FragmentHomeBinding
import com.arum.movieinfo.model.*
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(), PopularListener, NowPlayingListener, UpComingListener {

    lateinit var binding: FragmentHomeBinding
    private var upcomingListSlider: List<UpcomingList>? = null
    private var upcomingListforRecycleView: List<UpcomingList>? = null
    val imageList = ArrayList<SlideModel>()
    private var popularAdapter: PopularAdapter? = null
    private var upcomingAdapter: UpComingAdapter? = null
    private var nowPlayingAdapter: NowPlayingAdapter? = null

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

        const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500/"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapterPopular()
        setUpAdapterNowPlaying()
        setUpAdapterUpComing()

        getDataFromApi()
    }

    private fun getDataFromApi() {
        getPopular()
        getNowPlaying()
        getMovieUpcoming()
    }

    private fun getMovieUpcoming() {
        NetworkConfig().getService()
            .getMovieUpcoming()
            .enqueue(object : Callback<UpcomingResponse> {
                override fun onResponse(
                    call: Call<UpcomingResponse>,
                    response: Response<UpcomingResponse>
                ) {
                    response.body()?.results.let {
                        upcomingListSlider = it?.shuffled()?.take(5)
                        upcomingListforRecycleView= it
                        upcomingListforRecycleView?.let {
                            upcomingAdapter?.update(it as MutableList<UpcomingList>)
                        }
                        setImageSlider()
                    }
                }

                override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {

                }
            })
    }



    private fun getNowPlaying() {
        NetworkConfig().getService()

            .getNowPlaying()

            .enqueue(object : Callback<NowPlayingResponse> {
                override fun onResponse(
                    call: Call<NowPlayingResponse>,
                    response: Response<NowPlayingResponse>
                ) {
                    response.body()?.results.let {
                        if (it != null) {
                            nowPlayingAdapter?.update(it)
                        }

                    }
                }

                override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {

                }
            })
    }

    private fun getPopular() {
        NetworkConfig().getService()
            .getPopular()
            .enqueue(object : Callback<PopularResponse> {
                override fun onResponse(
                    call: Call<PopularResponse>,
                    response: Response<PopularResponse>
                ) {
                    response.body()?.results.let {
                        if (it != null) {
                            popularAdapter?.update(it)
                        }

                    }
                }

                override fun onFailure(call: Call<PopularResponse>, t: Throwable) {

                }
            })
    }

    private fun setUpAdapterNowPlaying() {
        nowPlayingAdapter = NowPlayingAdapter(this)
        binding.rvNowPlaying.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }
    }

    private fun setUpAdapterPopular() {
        popularAdapter = PopularAdapter(this)
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
    }

    private fun setUpAdapterUpComing() {
        upcomingAdapter = UpComingAdapter(this)
        binding.rvUpComing.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingAdapter
        }
    }


    private fun setImageSlider() {
        val imageSlider = requireActivity().findViewById<ImageSlider>(R.id.image_slider)
        upcomingListSlider?.forEach {
            imageList.add(SlideModel(IMAGE_PATH + it.backdrop_path, it.title, ScaleTypes.FIT))
        }
        imageSlider.setImageList(imageList)
    }

    override fun NowPlayingOnClick(data: Results) {
        TODO("Not yet implemented")
    }

    override fun PopularOnClick(data: Resultss) {
        TODO("Not yet implemented")
    }

    override fun UpComingOnClick(data: UpcomingList) {
        TODO("Not yet implemented")
    }


}

