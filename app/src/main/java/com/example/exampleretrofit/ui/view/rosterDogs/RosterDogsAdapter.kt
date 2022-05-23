package com.example.exampleretrofit.ui.view.rosterDogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.exampleretrofit.databinding.ItemDogBinding

class RosterDogsAdapter(
    private val inflater: LayoutInflater,
) : ListAdapter<String, RosterDogsRowHolder>(DIffCallBack) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RosterDogsRowHolder = RosterDogsRowHolder(
        ItemDogBinding.inflate(inflater, parent, false)
    )

    override fun onBindViewHolder(holder: RosterDogsRowHolder, position: Int) =
        holder.bind(getItem(position))

}

 private object DIffCallBack: DiffUtil.ItemCallback<String>() {
     override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
         oldItem == newItem

     override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
         oldItem == newItem
 }
