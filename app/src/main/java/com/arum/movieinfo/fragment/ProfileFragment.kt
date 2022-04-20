package com.arum.movieinfo.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arum.movieinfo.LoginActivity
import com.arum.movieinfo.MainActivity
import com.arum.movieinfo.R
import com.arum.movieinfo.core.SharedPreference
import com.arum.movieinfo.databinding.FragmentHomeBinding
import com.arum.movieinfo.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var sharedPreference: SharedPreference

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
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
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = SharedPreference(requireContext())

        binding.tvName.text = sharedPreference.getName()
        Glide.with(requireContext())
            .load(sharedPreference.getImage())
            .into(binding.ivImage)

        //Logout action
        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            sharedPreference.clearSharedPreference()
        }
    }


}