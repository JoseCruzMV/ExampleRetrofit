package com.example.exampleretrofit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exampleretrofit.domain.GetDogByBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsByBreedViewModel @Inject constructor(
    private val getDogByBreedUseCase: GetDogByBreedUseCase
) : ViewModel() {
    val dogsImages = MutableLiveData<List<String>>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(query: String) {
        viewModelScope.launch {
            loading.postValue(true)
            val result = getDogByBreedUseCase(query = query)
            if(!result.isNullOrEmpty()) {
                dogsImages.postValue(result)
                loading.postValue(false)
            }
        }
    }

}