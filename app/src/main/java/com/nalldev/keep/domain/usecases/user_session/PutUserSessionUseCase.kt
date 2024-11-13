package com.nalldev.keep.domain.usecases.user_session

import com.nalldev.keep.domain.models.UserModel
import com.nalldev.keep.domain.repositories.UserSessionRepository

class PutUserSessionUseCase (
    private val userSessionRepository: UserSessionRepository
) {
    suspend operator fun invoke(user : UserModel) {
        return userSessionRepository.putUserSession(user)
    }
}