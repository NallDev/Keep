package com.nalldev.keep.data.datasource.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.nalldev.keep.domain.models.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PreferencesDataSource(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
    private val ioDispatcher: CoroutineDispatcher
) {

    companion object {
        private const val KEY_SESSION = "userSession"
    }

    suspend fun putUserSession(user: UserModel) = withContext(ioDispatcher) {
        val jsonString = gson.toJson(user)
        sharedPreferences.edit().putString(KEY_SESSION, jsonString).apply()
    }

    suspend fun getUserSession() : UserModel = withContext(ioDispatcher) {
        val jsonString = sharedPreferences.getString(KEY_SESSION, null)
        if (jsonString != null) {
            gson.fromJson(jsonString, UserModel::class.java)
        } else {
            UserModel(token = "")
        }
    }

    suspend fun removeUserSession()= withContext(ioDispatcher) {
        sharedPreferences.edit().remove(KEY_SESSION).apply()
    }
}