package com.asn.weatherportfolio.ui

import com.asn.weatherportfolio.model.CurrentWeather
import com.asn.weatherportfolio.model.DailyWeather
import com.asn.weatherportfolio.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherCommonViewModel() : ViewModel() {
    private val repository = WeatherRepository()
    var weatherStateFlow: MutableStateFlow<UiState?> = MutableStateFlow(null)

    fun getWeather(latitude: Double, longitude: Double) {
        weatherStateFlow.value = UiState.Loading
        viewModelScope.launch {
            repository.getWeatherFromRemote(latitude, longitude).collect {
                val dailyWeather = it.dailyResponse?.toDailyWeather()
                weatherStateFlow.value = UiState.Success(it.currentWeather, dailyWeather)
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val currentWeather: CurrentWeather?, val forecastWeather: List<DailyWeather>?) : UiState()
}