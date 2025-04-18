package com.example.lessons11.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val city: String,
    val lat:String,
    val lon:String
) : Parcelable