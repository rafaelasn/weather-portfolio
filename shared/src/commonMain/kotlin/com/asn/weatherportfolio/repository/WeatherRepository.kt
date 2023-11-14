package com.asn.weatherportfolio.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository() {

    private val api = RemoteDataSource()

    suspend fun getWeatherFromRemote() = flow {
        val latitude = -20.0
        val longitude = -54.0

        emit(api.getWeatherFromApi(latitude, longitude))
    }.flowOn(Dispatchers.IO)
}