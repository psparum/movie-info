package com.arum.movieinfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arum.bottomnavigation.network.NetworkConfig
import com.arum.movieinfo.R
import com.arum.movieinfo.databinding.FragmentHomeBinding
import com.arum.movieinfo.model.UpcomingList
import com.arum.movieinfo.model.UpcomingResponse
import com.arum.movieinfo.model.UserData
import com.arum.movieinfo.network.NetworkConfigLogin
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var upcomingList: List<UpcomingList>? = null
    val imageList = ArrayList<SlideModel>()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovieUpcoming()
    }

    private fun getMovieUpcoming() {
        NetworkConfig().getService()
            .getMovieUpcoming()
            .enqueue(object : Callback<UpcomingResponse> {
                override fun onResponse(call: Call<UpcomingResponse>, response: Response<UpcomingResponse>) {
                    response.body()?.let {
                        upcomingList = it.results.shuffled().take(5)
                        setImageSlider()
                    }
                }

                override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {

                }
            })
    }

    private fun setImageSlider() {
        val imageSlider = requireActivity().findViewById<ImageSlider>(R.id.image_slider)
        upcomingList?.forEach {
            imageList.add(SlideModel(IMAGE_PATH + it.backdrop_path, it.title, ScaleTypes.FIT))
        }
        imageSlider.setImageList(imageList)
    }


}