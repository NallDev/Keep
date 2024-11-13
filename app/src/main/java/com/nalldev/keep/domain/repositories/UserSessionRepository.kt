package com.nalldev.keep.domain.repositories

import com.nalldev.keep.domain.models.UserModel

interface UserSessionRepository {
    suspend fun putUserSession(user: UserModel)
    suspend fun getUserSession(): UserModel
    suspend fun removeUserSession()
}