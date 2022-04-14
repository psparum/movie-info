package com.arum.movieinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.arum.bottomnavigation.network.NetworkConfig
import com.arum.movieinfo.databinding.ActivityLoginBinding
import com.arum.movieinfo.model.*
import com.arum.movieinfo.network.NetworkConfigLogin
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private var isShowPassword = false
    private var isUsernameEmpty = false
    private var isPasswordEmpty = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun postLogin() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        NetworkConfigLogin().getService()
            .postLogin(username, password)
            .enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    response.body()?.let {
                       Toast.makeText(this@LoginActivity, it.toString(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {}
            })
    }

    private fun initView() {
        binding.apply {
            btnLogin.setOnClickListener {
                postLogin()
            }
            etPassword.addTextChangedListener {
                it?.let {
                    if (it.isNotEmpty()) {
                        tvShowpw.visibility = View.VISIBLE
                        isPasswordEmpty = true
                    }else {
                        tvShowpw.visibility = View.GONE
                        isPasswordEmpty = false
                    }

                    if (isUsernameEmpty && isPasswordEmpty) {
                        btnLogin.isEnabled = true
                        btnLogin.setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.merah_bata))
                    }else {
                        btnLogin.isEnabled = false
                        btnLogin.setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.abu_tua))
                    }
                }
            }

            etUsername.addTextChangedListener {
                it?.let {
                    if (it.isNotEmpty()) {
                        isUsernameEmpty = true
                    }else {
                        isUsernameEmpty = false
                    }

                    if (isUsernameEmpty && isPasswordEmpty) {
                        btnLogin.isEnabled = true
                        btnLogin.setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.merah_bata))
                    }else {
                        btnLogin.isEnabled = false
                        btnLogin.setBackgroundColor(ContextCompat.getColor(this@LoginActivity, R.color.abu_tua))
                    }
                }
            }

            tvShowpw.setOnClickListener {
                if (!isShowPassword) { // false
                    tvShowpw.text = "HIDE"
                    isShowPassword = true
                    etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }else { // true
                    tvShowpw.text = "SHOW"
                    isShowPassword = false
                    etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                etPassword.setSelection(etPassword.length())
            }
        }
    }
}