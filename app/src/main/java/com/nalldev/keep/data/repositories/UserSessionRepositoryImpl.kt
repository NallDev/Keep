package com.nalldev.keep.data.repositories

import com.nalldev.keep.data.datasource.preferences.PreferencesDataSource
import com.nalldev.keep.domain.models.UserModel
import com.nalldev.keep.domain.repositories.UserSessionRepository

class UserSessionRepositoryImpl (
    private val preferencesDataSource: PreferencesDataSource
) : UserSessionRepository {
    override suspend fun putUserSession(user: UserModel) {
        return preferencesDataSource.putUserSession(user)
    }

    override suspend fun getUserSession() : UserModel {
        return preferencesDataSource.getUserSession()
    }

    override suspend fun removeUserSession() {
        return preferencesDataSource.removeUserSession()
    }
}