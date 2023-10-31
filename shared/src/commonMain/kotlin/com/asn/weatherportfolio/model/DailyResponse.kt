package com.asn.weatherportfolio.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyResponse(
    @SerialName("time")                             var time: ArrayList<String> = arrayListOf(),
    @SerialName("weathercode")                      var weatherCode: ArrayList<Int> = arrayListOf(),
    @SerialName("temperature_2m_max")               var tempMax: ArrayList<Double> = arrayListOf(),
    @SerialName("temperature_2m_min")               var tempMin: ArrayList<Double> = arrayListOf(),
    @SerialName("precipitation_probability_max")    var precipitationProbability: ArrayList<Int> = arrayListOf(),
    @SerialName("windspeed_10m_max")                var windSpeed: ArrayList<Double> = arrayListOf()
)