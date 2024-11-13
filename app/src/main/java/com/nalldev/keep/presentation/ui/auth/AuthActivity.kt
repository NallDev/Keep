package com.nalldev.keep.presentation.ui.auth

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nalldev.keep.R
import com.nalldev.keep.databinding.ActivityAuthBinding
import com.nalldev.keep.utils.CommonHelper
import com.nalldev.keep.utils.clearAllFocus
import com.nalldev.keep.utils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CommonHelper.useLightTheme(this)

        initObserver()
        initListener()
    }

    private fun initObserver() = with(viewModel) {
        motionProgress.observe(this@AuthActivity) { progress ->
            binding.motionLayout.progress = progress
        }

        transitionState.observe(this@AuthActivity) { motionState ->
            if (motionState == AuthViewModel.TransitionState.START) {
                binding.motionLayout.post {
                    binding.motionLayout.transitionToStart()
                }
            }
            if (motionState == AuthViewModel.TransitionState.END) {
                binding.motionLayout.post {
                    binding.motionLayout.transitionToEnd()
                }
            }

            clearInput()
        }
    }

    private fun initListener() = with(binding) {
        onBackPressedDispatcher.addCallback {
            if (binding.motionLayout.progress > 0.0f) {
                binding.tvLogin.performClick()
            } else {
                finish()
            }
        }

        tvLogin.setOnClickListener {
            viewModel.setTransitionState(AuthViewModel.TransitionState.START)
        }

        tvRegister.setOnClickListener {
            viewModel.setTransitionState(AuthViewModel.TransitionState.END)
        }

        binding.btnRegister.setOnClickListener {
            viewModel.doRegister(
                username = binding.edRegisterName.text.toString(),
                email = binding.edRegisterEmail.text.toString(),
                password = binding.edRegisterPassword.text.toString()
            )
        }

        binding.btnLogin.setOnClickListener {
            viewModel.doLogin(
                username = binding.edLoginUsernmae.text.toString(),
                password = binding.edLoginPassword.text.toString()
            )
        }
    }

    private fun clearInput() {
        CommonHelper.clearEditTexts(listOf(
            binding.edRegisterName,
            binding.edRegisterEmail,
            binding.edRegisterPassword,
            binding.edLoginEmail,
            binding.edLoginPassword
        ))
        hideKeyboard()
        clearAllFocus()
    }
}