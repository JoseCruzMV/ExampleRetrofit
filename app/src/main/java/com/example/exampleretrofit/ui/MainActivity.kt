package com.example.exampleretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampleretrofit.databinding.ActivityMainBinding
import com.example.exampleretrofit.ui.view.rosterDogs.RosterDogsAdapter
import com.example.exampleretrofit.ui.viewmodel.DogsByBreedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private val dogsByBreedViewModel: DogsByBreedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        binding.pbLoading.isVisible = false

        val adapter = RosterDogsAdapter(layoutInflater)
        binding.rvDogs.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context)
        }

        dogsByBreedViewModel.dogsImages.observe(this) { currentDog ->
            adapter.submitList(currentDog)
        }
        dogsByBreedViewModel.loading.observe(this) { loadingStatus ->
            binding.pbLoading.isVisible = loadingStatus
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        dogsByBreedViewModel.onCreate(query = query ?: "")
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean = true

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.vrDogs.windowToken, 0)
    }
}