package com.example.exampleretrofit.data

import com.example.exampleretrofit.data.network.DogsService
import javax.inject.Inject


class DogRepository @Inject constructor(
    private val service: DogsService
) {
    suspend fun getDogByBreed(query: String): List<String> {
        val response = service.getDogByBreed(query = query)
        return response?.images ?: listOf("Dog don´t found")
    }
}