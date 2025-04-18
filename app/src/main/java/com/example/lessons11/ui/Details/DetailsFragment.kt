package com.example.lessons11.ui.Details

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Visibility
import com.example.lessons11.R
import com.example.lessons11.data.model.Weather
import com.example.lessons11.data.model.WeatherDTO
import com.example.lessons11.data.repositories.WeatherLoader
import com.example.lessons11.data.repositories.WeatherLoaderListener
import com.example.lessons11.databinding.FragmentDetailsBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val YOUR_API_KEY = "162e4d52-b54a-49ec-9e24-09ce3d5366f3"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
     private lateinit var weatherBundle: Weather
    companion object {
        const val  BUNDLE_NAME = "WEATHER"
        fun newInstance(bundle: Bundle):DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_NAME) ?: Weather()
        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        loadWeather()
    }


    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
          // метод загузки данных в ui
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE

            val city = weatherBundle.city
            cityCoordinates.text = String.format(
                city.lat.toString(),
                city.lon.toString()
            )
            temperatureValue.text = weatherDTO.fact?.temp.toString()

            conditionValue.text = weatherDTO.fact?.condition.toString()
            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadWeather() {
     // написать по аналогии браузера хождение в браузер
        val weatherLoader = WeatherLoader(object : WeatherLoaderListener {
            override fun onLoaded(weatherDTO: WeatherDTO): WeatherDTO {
                displayWeather(weatherDTO)
                return weatherDTO

            }

            override fun onFailed(throwable: Throwable): Throwable {
                TODO("Not yet implemented")
            }

        })

        weatherLoader.loadWeather()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        // тоже по аналогии получение буф
        return reader.lines().collect(Collectors.joining("\n"))
    }
}
