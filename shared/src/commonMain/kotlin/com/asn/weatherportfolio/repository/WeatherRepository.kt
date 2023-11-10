package com.asn.weatherportfolio.repository

import com.asn.weatherportfolio.model.WeatherResponse
import kotlinx.coroutines.flow.flow

class WeatherRepository() {

    private val api = RemoteDataSource()

    suspend fun getWeatherFromRemote() = flow {
        val latitude = -20.0
        val longitude = -50.0

        emit(api.getWeatherFromApi(latitude, longitude))
    }
}