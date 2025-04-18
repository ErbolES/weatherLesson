package com.example.lessons11.data.model

data class WeatherDTO(
    val fact: FactDTO? = null
    )

data class FactDTO(
    val temp: Int?,
    val feels_like: Int,
    val condition: String?)
// здесь жно содать два дто класса