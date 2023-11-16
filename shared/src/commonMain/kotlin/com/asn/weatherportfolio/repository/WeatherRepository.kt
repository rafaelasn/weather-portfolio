package com.asn.weatherportfolio.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository() {

    private val api = RemoteDataSource()

    suspend fun getWeatherFromRemote(latitude: Double, longitude: Double) = flow {
        emit(api.getWeatherFromApi(latitude, longitude))
    }.flowOn(Dispatchers.IO)
}