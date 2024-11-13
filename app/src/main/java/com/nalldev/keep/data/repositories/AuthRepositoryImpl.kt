package com.nalldev.keep.data.repositories

import com.nalldev.keep.data.datasource.network.NetworkDataSources
import com.nalldev.keep.domain.models.LoginModel
import com.nalldev.keep.domain.models.RegisterModel
import com.nalldev.keep.domain.repositories.AuthRepository
import com.nalldev.keep.utils.Mapper

class AuthRepositoryImpl(
    private val networkDataSources: NetworkDataSources
) : AuthRepository {
    override suspend fun register(request: RegisterModel): Boolean {
        return networkDataSources.register(Mapper.registerToEntity(request)).errorMessage != null
    }

    override suspend fun login(request: LoginModel): String {
        return networkDataSources.login(Mapper.loginToEntity(request)).data?.token ?: ""
    }
}