package com.nalldev.keep.domain.usecases.user_session

import com.nalldev.keep.domain.repositories.UserSessionRepository

class RemoveUserSessionUseCase (
    private val userSessionRepository: UserSessionRepository
) {
    suspend operator fun invoke() {
        return userSessionRepository.removeUserSession()
    }
}