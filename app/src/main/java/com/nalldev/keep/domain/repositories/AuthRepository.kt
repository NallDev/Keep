package com.nalldev.keep.domain.repositories

import com.nalldev.keep.domain.models.LoginModel
import com.nalldev.keep.domain.models.RegisterModel


interface AuthRepository {
    suspend fun register(request : RegisterModel) : Boolean
    suspend fun login(request : LoginModel) : String
}