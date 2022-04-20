package com.arum.movieinfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arum.movieinfo.R
import com.arum.movieinfo.databinding.FragmentFavoriteBinding
import com.arum.movieinfo.databinding.FragmentHomeBinding

class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding

    companion object {
        fun newInstance(): FavoriteFragment {
            val fragment = FavoriteFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }


}