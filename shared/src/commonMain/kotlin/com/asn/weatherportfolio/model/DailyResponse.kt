package com.asn.weatherportfolio.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyResponse (

    @SerialName("time"                          ) var time                        : ArrayList<String> = arrayListOf(),
    @SerialName("weathercode"                   ) var weathercode                 : ArrayList<Int>    = arrayListOf(),
    @SerialName("temperature_2m_max"            ) var temperature2mMax            : ArrayList<Double> = arrayListOf(),
    @SerialName("temperature_2m_min"            ) var temperature2mMin            : ArrayList<Double> = arrayListOf(),
    @SerialName("precipitation_probability_max" ) var precipitationProbabilityMax : ArrayList<Int>    = arrayListOf(),
    @SerialName("windspeed_10m_max"             ) var windspeed10mMax             : ArrayList<Double> = arrayListOf()

)