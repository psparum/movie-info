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
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private var isShowPassword = false
    private var isUsernameEmpty = false
    private var isPasswordEmpty = false
    private var requestToken = ""
    private var sessionID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        getRequestToken()
    }

    // 1. Get Request Token
    private fun getRequestToken() {
        NetworkConfig().getService()
            .getRequestToken()
            .enqueue(object : Callback<RequestTokenResponse> {
                override fun onResponse(call: Call<RequestTokenResponse>, response: Response<RequestTokenResponse>) {
                    response.body()?.let {
                        // 2. Jika Sukses simpan Request Token
                        requestToken = it.request_token?: ""
                    }
                }

                override fun onFailure(call: Call<RequestTokenResponse>, t: Throwable) {}
            })
    }

    private fun postLogin() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val requestData = LoginDataRequest(username, password, requestToken)

        NetworkConfig().getService()
            .postLoginData(request = requestData)
            .enqueue(object : Callback<LoginDataResponse> {
                override fun onResponse(call: Call<LoginDataResponse>, response: Response<LoginDataResponse>) {
                    response.body()?.let {
                        if (it.success == true) {
                            // 4. Get Session Data
                            getSessionData()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginDataResponse>, t: Throwable) {}
            })
    }

    private fun getSessionData() {
        val requestData = GetSessionRequest(requestToken)
        NetworkConfig().getService()
            .getSessionData(request = requestData)
            .enqueue(object : Callback<GetSessionResponse> {
                override fun onResponse(call: Call<GetSessionResponse>, response: Response<GetSessionResponse>) {
                    response.body()?.let {
                        // 5. Simpan Session ID dan Get User Data
                        sessionID = it.session_id
                        getUserData()
                    }
                }

                override fun onFailure(call: Call<GetSessionResponse>, t: Throwable) {}
            })
    }

    private fun getUserData() {
        NetworkConfig().getService()
            .getUserData(session_id = sessionID)
            .enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    response.body()?.let {
                        Toast.makeText(this@LoginActivity, "welcome ${it.username}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {}
            })
    }

    private fun initView() {
        binding.apply {
            btnLogin.setOnClickListener {
                // 3. jika validasi berhasil panggil API login
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