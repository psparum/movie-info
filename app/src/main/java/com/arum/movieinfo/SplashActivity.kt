package com.arum.movieinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.arum.movieinfo.core.SharedPreference
import com.arum.movieinfo.databinding.ActivityMainBinding
import com.arum.movieinfo.databinding.ActivitySplashBinding
import okhttp3.internal.http2.Http2Reader

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = SharedPreference(this)

        // timer 2 second sebelum manggil kodingan di dalemnya
        Handler(Looper.getMainLooper()).postDelayed({
            //kodingan ini berjalan setelah 2 detik
            if (sharedPreference.getName().isNullOrBlank()) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent) // Pindah ke Login Activity setelah 3 detik
                finish()
            }else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent) // Pindah ke Home Activity setelah 3 detik
                finish()
            }

        }, 2000 )

    }
}