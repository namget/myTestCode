package com.namget.testcode.ui.login

import android.content.Intent
import android.os.Bundle
import com.namget.testcode.R
import com.namget.testcode.databinding.ActivityLoginBinding
import com.namget.testcode.ext.toastMakeToast
import com.namget.testcode.ui.base.BaseActivity
import com.namget.testcode.ui.main.MainActivity
import com.namget.testcode.util.EventObserver

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(LoginViewModel::class) {

    override fun onLayoutId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        observeView()
    }

    override fun onResume() {
        super.onResume()
    }

    fun setupViewModel() {
        binding.viewModel = viewModel
    }

    fun observeView() {
        viewModel.toastMessage.observe(this, EventObserver {
            toastMakeToast(it)
        })

        viewModel.startMainActivityEvent.observe(this, EventObserver {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }




}