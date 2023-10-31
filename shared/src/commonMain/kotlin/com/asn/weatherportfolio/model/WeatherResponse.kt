package com.asn.weatherportfolio.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("latitude")             var latitude: Double? = null,
    @SerialName("longitude")            var longitude: Double? = null,
    @SerialName("generationtime_ms")    var generationtimeMs: Double? = null,
    @SerialName("utc_offset_seconds")   var utcOffsetSeconds: Int? = null,
    @SerialName("timezone")             var timezone: String? = null,
    @SerialName("timezone_abbreviation")var timezoneAbbreviation: String? = null,
    @SerialName("elevation")            var elevation: Int? = null,
    @SerialName("current")              var currentWeather: CurrentWeather? = CurrentWeather(),
    @SerialName("daily")                var dailyResponse: DailyResponse? = DailyResponse()
)