package com.nalldev.keep.presentation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.keep.domain.models.UserModel
import com.nalldev.keep.domain.usecases.auth.AuthUseCases
import com.nalldev.keep.domain.usecases.user_session.UserSessionUseCases
import com.nalldev.keep.utils.SingleLiveEvent
import com.nalldev.keep.utils.UIState
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCases: AuthUseCases,
    private val userSessionUseCases: UserSessionUseCases
) : ViewModel() {
    private val _motionProgress: MutableLiveData<Float> = MutableLiveData(0.0f)
    val motionProgress: LiveData<Float> = _motionProgress

    private val _transitionState: MutableLiveData<TransitionState> = MutableLiveData()
    val transitionState: LiveData<TransitionState> = _transitionState

    private val _toastEvent = SingleLiveEvent<String>()
    val toastEvent: LiveData<String> = _toastEvent

    private val _registerResult : MutableLiveData<UIState<Boolean>> = MutableLiveData()
    val registerResult: LiveData<UIState<Boolean>> = _registerResult

    private val _loginResult : MutableLiveData<UIState<Boolean>> = MutableLiveData()
    val loginResult: LiveData<UIState<Boolean>> = _loginResult

    fun doLogin(username: String, password: String) = viewModelScope.launch {
        _loginResult.postValue(UIState.Loading)
        try {
            val result = authUseCases.doLogin(username, password)
            if (result.isNotBlank()) {
                putUserSession(result)
            } else {
                _loginResult.postValue(UIState.Error)
                _toastEvent.postValue("Username or password is wrong")
            }
        } catch (e: Exception) {
            _loginResult.postValue(UIState.Error)
            _toastEvent.postValue("Username or password is wrong")
            println(e.message)
        }
    }

    fun doRegister(username: String, email : String, password: String) = viewModelScope.launch  {
        _registerResult.postValue(UIState.Loading)
        try {
            val isSuccess = authUseCases.doRegister(username, email, password)
            if (isSuccess) {
                _registerResult.postValue(UIState.Success(true))
                _transitionState.postValue(TransitionState.START)
                _toastEvent.postValue("Great, you are registered now")
            } else {
                _registerResult.postValue(UIState.Error)
                _toastEvent.postValue("Check your input")
            }
        } catch (e : Exception) {
            _registerResult.postValue(UIState.Error)
            _toastEvent.postValue("Check your input")
        }
    }

    private fun putUserSession(token: String) = viewModelScope.launch {
        try {
            userSessionUseCases.putUserSession(UserModel(token))
            _loginResult.postValue(UIState.Success(true))
            _toastEvent.postValue("Welcome to Keep")
        } catch (e : Exception) {
            _loginResult.postValue(UIState.Error)
            _toastEvent.postValue("Username or password is wrong")
        }
    }

    fun putMotionProgress(progress: Float) {
        _motionProgress.postValue(progress)
    }

    fun setTransitionState(state: TransitionState) {
        _transitionState.postValue(state)
    }

    enum class TransitionState {
        START, END
    }
}