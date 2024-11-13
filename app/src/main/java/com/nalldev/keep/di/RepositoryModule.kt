package com.nalldev.keep.di

import com.nalldev.keep.data.repositories.AuthRepositoryImpl
import com.nalldev.keep.data.repositories.UserSessionRepositoryImpl
import com.nalldev.keep.domain.repositories.AuthRepository
import com.nalldev.keep.domain.repositories.UserSessionRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserSessionRepository> {UserSessionRepositoryImpl(get())}
    single<AuthRepository> {AuthRepositoryImpl(get())}
}