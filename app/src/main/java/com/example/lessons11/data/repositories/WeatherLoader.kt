package com.example.lessons11.data.repositories

import java.net.URL
import javax.net.ssl.HttpsURLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import com.google.gson.Gson
import com.example.lessons11.data.model.Weather
import com.example.lessons11.data.model.WeatherDTO

class WeatherLoader(
    private val listener: WeatherLoaderListener
) {

    fun loadWeather() {
        Thread {
            lateinit var connection: HttpsURLConnection
            try {
                val uri =  URL("https://api.weather.yandex.ru/v2/informers?lat=52.37125&lon=4.89388")
                connection = uri.openConnection() as HttpsURLConnection
                connection.requestMethod = "GET"
                connection.addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)

                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val json = getLines(reader)
                val weatherDTO = Gson().fromJson(json, WeatherDTO::class.java)

                listener.onLoaded(weatherDTO)

            } catch (e: Exception) {
                listener.onFailed(e)
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun getLines(reader: BufferedReader): String {
        val result = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            result.append(line)
            line = reader.readLine()
        }
        return result.toString()
    }
}