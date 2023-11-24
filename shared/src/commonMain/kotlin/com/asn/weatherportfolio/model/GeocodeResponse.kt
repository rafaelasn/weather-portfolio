package com.asn.weatherportfolio.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodeResponse(
    @SerialName("features") var features: ArrayList<Features> = arrayListOf()
) {
    @Serializable
    data class Features(
        @SerialName("type") var type: String? = null,
        @SerialName("properties") var properties: Properties? = Properties()
    )

    @Serializable
    data class Properties(
        @SerialName("country") var country: String? = null,
        @SerialName("country_code") var countryCode: String? = null,
        @SerialName("region") var region: String? = null,
        @SerialName("state") var state: String? = null,
        @SerialName("county") var county: String? = null,
        @SerialName("city") var city: String? = null,
        @SerialName("state_code") var stateCode: String? = null,
        @SerialName("formatted") var formatted: String? = null,
        @SerialName("address_line1") var addressLine1: String? = null,
        @SerialName("address_line2") var addressLine2: String? = null,
        @SerialName("place_id") var placeId: String? = null
    )
}