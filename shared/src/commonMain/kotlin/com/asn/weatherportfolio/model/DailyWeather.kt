package com.asn.weatherportfolio.model

import kotlinx.serialization.SerialName

data class DailyWeather(
    var time: String,
    var weatherCode: Int,
    var tempMax: Double,
    var tempMin: Double,
    var precipitationProbability: Int,
    var windSpeed: Double
)
