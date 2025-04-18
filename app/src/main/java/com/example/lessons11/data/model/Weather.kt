package com.example.lessons11.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefault(),
    val temperature:Int = 0
) : Parcelable

fun getDefault() = City("Астана" , "2312123" , "123123123")

fun getCities() = listOf(
    Weather(City("Астана", "51.1605", "71.4704"), 1),
    Weather(City("Павлодар", "52.2870", "76.9674"), 2),
    Weather(City("Караганда", "49.8047", "73.1094"), 3),
    Weather(City("Атырау", "47.0945", "51.9237"), 4),
    Weather(City("Костанай", "53.2144", "63.6246"), 5),
    Weather(City("Алматы", "43.2389", "76.8897"), 6)
)
