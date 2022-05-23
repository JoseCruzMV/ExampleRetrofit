package com.example.exampleretrofit.ui.rosterDogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleretrofit.R

class RosterDogsAdapter(
    val images: List<String>,
) : RecyclerView.Adapter<RosterDogsRowHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RosterDogsRowHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RosterDogsRowHolder(layoutInflater.inflate(R.layout.item_dog , parent, false))
    }

    override fun onBindViewHolder(holder: RosterDogsRowHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = images.size

}