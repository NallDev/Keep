package com.nalldev.keep.domain.usecases.auth

import com.nalldev.keep.domain.models.LoginModel
import com.nalldev.keep.domain.repositories.AuthRepository

class DoLogin(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) : String {
        return authRepository.login(LoginModel(username, password))
    }
}