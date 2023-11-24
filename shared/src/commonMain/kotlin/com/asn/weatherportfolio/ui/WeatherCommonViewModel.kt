package com.asn.weatherportfolio.ui

import com.asn.weatherportfolio.model.CurrentWeather
import com.asn.weatherportfolio.model.DailyWeather
import com.asn.weatherportfolio.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherCommonViewModel() : ViewModel() {
    private val repository = WeatherRepository()

    private var lastLocation: Location? = null
    private var dailyWeather: List<DailyWeather>? = null
    private var currentWeather: CurrentWeather? = null
    private var locationName: String = ""

    var weatherStateFlow: MutableStateFlow<UiState?> = MutableStateFlow(null)

    fun getWeather() {
        weatherStateFlow.value = UiState.Loading
        viewModelScope.launch {
            if (lastLocation != null) {
                repository.getWeatherFromRemote(
                    lastLocation!!.latitude,
                    lastLocation!!.longitude
                ).collect {
                    currentWeather = it.currentWeather
                    dailyWeather = it.dailyResponse?.toDailyWeather()
                    weatherStateFlow.value = UiState.Success(currentWeather, dailyWeather, locationName)
                }
            }
        }
    }

    fun getReverseGeocode() {
        viewModelScope.launch {
            lastLocation?.let {
                repository.getReverseGeocodingFromApi(
                    it.latitude,
                    it.longitude
                ).collect { geo ->
                    val firstRef = geo.features.first().properties
                    val geoName = firstRef?.city ?: firstRef?.country ?: "Desconhecido"
                    locationName = geoName
                    weatherStateFlow.value = UiState.Success(currentWeather, dailyWeather, locationName)
                }
            }
        }
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        lastLocation = Location(latitude, longitude)
        getWeather()
        getReverseGeocode()
    }
}

data class Location(val latitude: Double, val longitude: Double)

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val currentWeather: CurrentWeather?,
        val forecastWeather: List<DailyWeather>?,
        val locationName: String
    ) : UiState()
}