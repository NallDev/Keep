package com.nalldev.keep

import android.app.Application
import android.content.pm.ApplicationInfo
import com.nalldev.keep.di.commonModule
import com.nalldev.keep.di.dataSourceModule
import com.nalldev.keep.di.networkModule
import com.nalldev.keep.di.repositoryModule
import com.nalldev.keep.di.useCaseModule
import com.nalldev.keep.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Keep : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
                androidLogger(Level.INFO)
            } else {
                androidLogger(Level.NONE)
            }
            androidContext(this@Keep)
            modules(listOf(
                commonModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                networkModule,
            ))
        }
    }
}