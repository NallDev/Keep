package com.nalldev.keep.domain.usecases.auth

import com.nalldev.keep.domain.models.RegisterModel
import com.nalldev.keep.domain.repositories.AuthRepository

class DoRegister(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username : String, email : String, password : String) : Boolean {
        return authRepository.register(RegisterModel(username, email, password))
    }
}