package com.nalldev.keep.data.datasource.network

import com.nalldev.keep.data.datasource.network.api.ApiService
import com.nalldev.keep.data.datasource.network.models.auh.AuthResponseModel
import com.nalldev.keep.data.datasource.network.models.auh.LoginRequestModel
import com.nalldev.keep.data.datasource.network.models.auh.RegisterRequestModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NetworkDataSources(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {
   suspend fun register(request: RegisterRequestModel) : AuthResponseModel = withContext(ioDispatcher) {
       apiService.register(request)
   }

    suspend fun login(request: LoginRequestModel) : AuthResponseModel = withContext(ioDispatcher) {
        apiService.login(request)
    }
}