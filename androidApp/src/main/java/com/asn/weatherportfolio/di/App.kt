package com.asn.weatherportfolio.di

import android.app.Application
import com.asn.weatherportfolio.ui.WeatherCommonViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    val androidModule = module {
        single { WeatherCommonViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(androidModule + appModule())
        }
    }
}