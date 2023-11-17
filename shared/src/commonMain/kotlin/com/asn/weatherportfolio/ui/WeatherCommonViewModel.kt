package com.asn.weatherportfolio.ui

import com.asn.weatherportfolio.model.CurrentWeather
import com.asn.weatherportfolio.model.DailyWeather
import com.asn.weatherportfolio.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

class WeatherCommonViewModel() : ViewModel() {
    private val repository = WeatherRepository()

    private val lastLocation: MutableStateFlow<Location?> = MutableStateFlow(null)
    var weatherStateFlow: MutableStateFlow<UiState?> = MutableStateFlow(null)

    fun getWeather() {
        weatherStateFlow.value = UiState.Loading
        viewModelScope.launch {
            if (lastLocation.value != null) {
                repository.getWeatherFromRemote(
                    lastLocation.value!!.latitude,
                    lastLocation.value!!.longitude
                ).collect {
                    val dailyWeather = it.dailyResponse?.toDailyWeather()
                    weatherStateFlow.value = UiState.Success(it.currentWeather, dailyWeather)
                }
            }
        }
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        lastLocation.value = Location(latitude, longitude)
        getWeather()
    }
}

data class Location(val latitude: Double, val longitude: Double)

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val currentWeather: CurrentWeather?,
        val forecastWeather: List<DailyWeather>?
    ) : UiState()
}