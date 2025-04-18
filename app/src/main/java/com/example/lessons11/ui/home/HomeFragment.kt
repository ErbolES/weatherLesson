package com.example.lessons11.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lessons11.R
import com.example.lessons11.data.model.Weather
import com.example.lessons11.databinding.FragmentHomeBinding
import com.example.lessons11.ui.Details.DetailsFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val adapter = MainFragmentAdapter(object :OnItemViewCliclListner{
        override fun onItemViewClick(weather: Weather) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.BUNDLE_NAME, weather)
                    }))
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecycleView.adapter = adapter
        binding.mainFrgamentFAB.setOnClickListener {

        }
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getWeather()
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.mainFragmnetLoadingLayout.visibility = View.GONE
                /*Snackbar.make(binding.mainView,"Error",Snackbar.LENGTH_INDEFINITE).setAction("Reload"){

                }*/
            }

            AppState.Loading -> {
                binding.mainFragmnetLoadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                adapter.setWeather(appState.weatherData)
                binding.mainFragmnetLoadingLayout.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.removeListener()
        _binding = null
    }
}

interface OnItemViewCliclListner{
    fun onItemViewClick(weather: Weather)
}