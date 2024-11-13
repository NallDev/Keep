package com.nalldev.keep.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.nalldev.keep.data.datasource.network.NetworkDataSources
import com.nalldev.keep.data.datasource.preferences.PreferencesDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun providePreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("keep", Context.MODE_PRIVATE)
}

fun provideGson() : Gson {
    return Gson()
}

val dataSourceModule = module {
    single { providePreferences(get()) }
    single { provideGson() }
    single { PreferencesDataSource(get(), get(), get(named("IODispatcher"))) }
    single { NetworkDataSources(get(), get()) }
}