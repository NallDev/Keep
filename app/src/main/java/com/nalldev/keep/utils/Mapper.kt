package com.nalldev.keep.utils

import com.nalldev.keep.data.datasource.network.models.auh.LoginRequestModel
import com.nalldev.keep.data.datasource.network.models.auh.RegisterRequestModel
import com.nalldev.keep.domain.models.LoginModel
import com.nalldev.keep.domain.models.RegisterModel

object Mapper {
    fun registerToEntity(registerModel: RegisterModel) = RegisterRequestModel(
        username = registerModel.username,
        email = registerModel.email,
        password = registerModel.password
    )

    fun loginToEntity(loginModel: LoginModel) = LoginRequestModel(
        username = loginModel.username,
        password = loginModel.password
    )
}