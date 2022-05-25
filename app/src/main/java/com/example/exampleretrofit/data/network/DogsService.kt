package com.example.exampleretrofit.data.network

import com.example.exampleretrofit.core.RetrofitHelper
import com.example.exampleretrofit.data.model.DogsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogsService @Inject constructor(
    private val api: DogsApiService
) {

    suspend fun getDogByBreed(query: String): DogsResponse? = withContext(Dispatchers.IO) {
        val response = api.getDogsByBreeds("$query/images")
        response.body()
    }
}