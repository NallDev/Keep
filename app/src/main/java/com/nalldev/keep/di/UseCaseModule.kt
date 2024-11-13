package com.nalldev.keep.di

import com.nalldev.keep.domain.usecases.auth.AuthUseCases
import com.nalldev.keep.domain.usecases.auth.DoLogin
import com.nalldev.keep.domain.usecases.auth.DoRegister
import com.nalldev.keep.domain.usecases.user_session.GetUserSessionUseCase
import com.nalldev.keep.domain.usecases.user_session.PutUserSessionUseCase
import com.nalldev.keep.domain.usecases.user_session.RemoveUserSessionUseCase
import com.nalldev.keep.domain.usecases.user_session.UserSessionUseCases
import org.koin.dsl.module

val useCaseModule =  module {
    single { GetUserSessionUseCase(get()) }
    single { PutUserSessionUseCase(get()) }
    single { RemoveUserSessionUseCase(get()) }
    single { UserSessionUseCases(get(), get(), get()) }

    single { DoLogin(get()) }
    single { DoRegister(get()) }
    single { AuthUseCases(get(), get()) }
}