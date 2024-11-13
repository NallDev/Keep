package com.nalldev.keep.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nalldev.keep.R
import com.nalldev.keep.databinding.ActivitySplashBinding
import com.nalldev.keep.presentation.ui.auth.AuthActivity
import com.nalldev.keep.presentation.ui.home.MainActivity
import com.nalldev.keep.utils.CommonHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<SplashViewModel>()

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
        motionProgress.observe(this@SplashActivity) { progress ->
            binding.root.progress = progress
        }

        hasSession.observe(this@SplashActivity) { hasSession ->
            if (hasSession) {
                navigateToMainActivity()
            } else {
                navigateToAuthActivity()
            }
        }
    }

    private fun initListener() = with(binding) {
        root.post {
            root.transitionToEnd {
                viewModel.checkUserSession()
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun navigateToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        viewModel.storeMotionProgress(binding.root.progress)
    }
}