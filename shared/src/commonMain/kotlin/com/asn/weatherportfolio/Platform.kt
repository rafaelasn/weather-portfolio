package com.asn.weatherportfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform