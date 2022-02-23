package com.arum.movieinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.arum.movieinfo.databinding.ActivityLoginBinding

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

    private fun initView() {
        binding.apply {
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
                    tvShowpw.text = "Hide password"
                    isShowPassword = true
                    etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }else { // true
                    tvShowpw.text = "Show password"
                    isShowPassword = false
                    etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                etPassword.setSelection(etPassword.length())
            }
        }
    }
}