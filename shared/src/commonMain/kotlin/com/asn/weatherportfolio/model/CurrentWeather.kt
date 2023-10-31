package com.asn.weatherportfolio.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather (
    @SerialName("time"                 ) var time                : String? = null,
    @SerialName("interval"             ) var interval            : Int?    = null,
    @SerialName("temperature_2m"       ) var temperature2m       : Double? = null,
    @SerialName("relativehumidity_2m"  ) var relativehumidity2m  : Int?    = null,
    @SerialName("apparent_temperature" ) var apparentTemperature : Double? = null,
    @SerialName("weathercode"          ) var weathercode         : Int?    = null,
    @SerialName("cloudcover"           ) var cloudcover          : Int?    = null,
    @SerialName("windspeed_10m"        ) var windspeed10m        : Double? = null
)