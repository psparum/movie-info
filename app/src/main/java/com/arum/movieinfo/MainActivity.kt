package com.arum.movieinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arum.movieinfo.databinding.ActivityMainBinding
import com.arum.movieinfo.fragment.FavoriteFragment
import com.arum.movieinfo.fragment.HomeFragment
import com.arum.movieinfo.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_satu -> {
                    val fragment = HomeFragment.newInstance()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_dua -> {
                    val fragment = FavoriteFragment()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_tiga -> {
                    val fragment = ProfileFragment()
                    addFragment(fragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        val fragment = HomeFragment.newInstance()
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

}