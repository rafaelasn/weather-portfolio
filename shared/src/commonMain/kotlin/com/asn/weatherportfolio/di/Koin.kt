package com.asn.weatherportfolio.di

import com.asn.weatherportfolio.repository.WeatherRepository
import org.koin.dsl.module

fun appModule() = module {
    single<WeatherRepository> { WeatherRepository() }
}