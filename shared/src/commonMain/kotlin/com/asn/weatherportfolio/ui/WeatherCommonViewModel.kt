package com.asn.weatherportfolio.ui

import com.asn.weatherportfolio.model.WeatherResponse
import com.asn.weatherportfolio.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherCommonViewModel() : ViewModel() {

    private val repository = WeatherRepository()

    private val weatherStateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)

    fun getWeather() {
        viewModelScope.launch {
            repository.getWeatherFromRemote().collect {
                weatherStateFlow.value = UiState.Success(it)
            }
        }
    }
}

sealed class UiState {
    object Initial : UiState()
    data class Success(val value: WeatherResponse) : UiState()
}