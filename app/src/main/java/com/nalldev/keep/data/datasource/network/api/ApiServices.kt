package com.nalldev.keep.data.datasource.network.api

import com.nalldev.keep.data.datasource.network.models.auh.AuthResponseModel
import com.nalldev.keep.data.datasource.network.models.auh.LoginRequestModel
import com.nalldev.keep.data.datasource.network.models.auh.RegisterRequestModel
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(
        @Body request: RegisterRequestModel
    ): AuthResponseModel

    @POST("login")
    suspend fun login(
        @Body request : LoginRequestModel
    ): AuthResponseModel
}