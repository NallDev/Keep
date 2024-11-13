package com.nalldev.keep.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.keep.domain.usecases.user_session.UserSessionUseCases
import com.nalldev.keep.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userSessionUseCases: UserSessionUseCases
) : ViewModel() {

    private val _motionProgress: MutableLiveData<Float> = MutableLiveData(0.0f)
    val motionProgress: LiveData<Float> = _motionProgress

    private val _hasSession = SingleLiveEvent<Boolean>()
    val hasSession: LiveData<Boolean> = _hasSession

    fun checkUserSession() = viewModelScope.launch {
        try {
            val userModel = userSessionUseCases.getUserSession()
            if (userModel.token.isNotEmpty()) {
                _hasSession.postValue(true)
            } else {
                _hasSession.postValue(false)
            }
        } catch (e : Exception) {
            _hasSession.postValue(false)
        }
    }

    fun storeMotionProgress(progress: Float) {
        _motionProgress.postValue(progress)
    }
}