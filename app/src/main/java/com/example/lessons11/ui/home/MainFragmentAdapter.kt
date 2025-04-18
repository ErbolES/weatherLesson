package com.example.lessons11.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lessons11.R
import com.example.lessons11.data.model.Weather

class MainFragmentAdapter(
    private var onItemViewCliclListner: OnItemViewCliclListner?
):RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var weatheData: List<Weather> = listOf()

    fun setWeather(data:List<Weather>){
        weatheData = data
        notifyDataSetChanged()
    }

    fun removeListener(){
        onItemViewCliclListner = null
    }

    inner class MainViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(weather: Weather){
            itemView.apply {
                findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text = weather.city.city
                setOnClickListener{
                    onItemViewCliclListner?.onItemViewClick(weather)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false) as View
        )
    }

    override fun getItemCount(): Int {
        return weatheData.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
         holder.bind(weatheData[position])
    }
}