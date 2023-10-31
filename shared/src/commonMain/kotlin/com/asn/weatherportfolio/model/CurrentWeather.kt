package com.asn.weatherportfolio.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("time")                 var time: String? = null,
    @SerialName("temperature_2m")       var currentTemp: Double? = null,
    @SerialName("relativehumidity_2m")  var humidity: Int? = null,
    @SerialName("apparent_temperature") var feelsLikeTemp: Double? = null,
    @SerialName("weathercode")          var weatherCode: Int? = null,
    @SerialName("cloudcover")           var cloudCover: Int? = null,
    @SerialName("windspeed_10m")        var windSpeed: Double? = null
)