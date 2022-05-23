package com.example.exampleretrofit.ui.view.rosterDogs

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleretrofit.R
import com.example.exampleretrofit.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class RosterDogsRowHolder(
    private val binding: ItemDogBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: String) {
        Picasso.get().load(image)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivDog)
    }
}