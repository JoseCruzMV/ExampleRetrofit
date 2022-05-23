package com.example.exampleretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampleretrofit.core.RetrofitHelper
import com.example.exampleretrofit.data.model.DogsResponse
import com.example.exampleretrofit.data.network.DogsApiService
import com.example.exampleretrofit.databinding.ActivityMainBinding
import com.example.exampleretrofit.ui.rosterDogs.RosterDogsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var dogImages: List<String>
    private lateinit var dogsAdapter: RosterDogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
    }

    private fun searchByName(query: String) {
        val retrofit = RetrofitHelper.getRetrofit()
        CoroutineScope(Dispatchers.IO).launch {
            val call = retrofit
                .create(DogsApiService::class.java)
                .getDogsByBreeds("$query/images")
            val imagesResult = call.body()

            runOnUiThread {
                if (imagesResult?.status == "success") {
                    initCharacter(imagesResult)
                } else {
                    showError("Error")
                }
            }
        }
    }

    private fun initCharacter(imagesResult: DogsResponse) {
        dogImages =
            if (imagesResult.status == "success") imagesResult.images
            else listOf("A", "B")
        dogsAdapter = RosterDogsAdapter(dogImages)
        binding.rvDogs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsAdapter
            setHasFixedSize(true)
        }
    }

    private fun showError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query = query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean = true


}