package com.nalldev.keep.domain.usecases.user_session

import com.nalldev.keep.domain.models.UserModel
import com.nalldev.keep.domain.repositories.UserSessionRepository

class GetUserSessionUseCase (
    private val userSessionRepository: UserSessionRepository
) {
    suspend operator fun invoke() : UserModel {
        return userSessionRepository.getUserSession()
    }
}