package com.asn.weatherportfolio.repository

import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository() {

    private val api = RemoteDataSource()

    suspend fun getWeatherFromRemote(latitude: Double, longitude: Double) = flow {
        emit(api.getWeatherFromApi(latitude, longitude))
    }.catch {
        KtorSimpleLogger("getWeatherApi").debug("Unknown Error")
    }.flowOn(Dispatchers.IO)

    suspend fun getReverseGeocodingFromApi(latitude: Double, longitude: Double) = flow {
        emit(api.getReverseGeocodingFromApi(latitude, longitude))
    }.catch {
        KtorSimpleLogger("ReverseGeocodeApi").debug("Unknown Error")
    }.flowOn(Dispatchers.IO)
}