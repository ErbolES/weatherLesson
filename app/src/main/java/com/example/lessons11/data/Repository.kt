package com.example.lessons11.data

import com.example.lessons11.data.model.Weather

interface Repository {
    fun getWeatherFromServer():Weather
    fun getWeatherFromLocalStorage():List<Weather>
}