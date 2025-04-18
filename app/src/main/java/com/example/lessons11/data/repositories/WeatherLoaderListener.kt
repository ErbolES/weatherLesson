package com.example.lessons11.data.repositories

import com.example.lessons11.data.model.WeatherDTO

interface WeatherLoaderListener {
    fun onLoaded(weatherDTO: WeatherDTO): WeatherDTO
    fun onFailed(throwable: Throwable): Throwable
}
