package com.example.exampleretrofit.domain

import com.example.exampleretrofit.data.DogRepository
import javax.inject.Inject

class GetDogByBreedUseCase @Inject constructor(
    private val repository: DogRepository

) {
    suspend operator fun invoke(query: String): List<String> =
        repository.getDogByBreed(query = query)
}