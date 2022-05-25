package com.example.exampleretrofit.domain

import com.example.exampleretrofit.data.DogRepository

class GetDogByBreedUseCase(
    private val repository: DogRepository,
) {
    suspend operator fun invoke(query: String): List<String> =
        repository.getDogByBreed(query = query)
}