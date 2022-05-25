package com.example.exampleretrofit.data

import com.example.exampleretrofit.data.network.DogsService


class DogRepository(
    private val service: DogsService
) {
    suspend fun getDogByBreed(query: String): List<String> {
        val response = service.getDogByBreed(query = query)
        return response?.images ?: listOf("Dog don´t found")
    }
}