package com.nalldev.keep.domain.usecases.auth

data class AuthUseCases(
    val doLogin: DoLogin,
    val doRegister: DoRegister
)
