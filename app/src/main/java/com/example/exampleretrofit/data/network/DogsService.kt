package com.example.exampleretrofit.data.network

import com.example.exampleretrofit.core.RetrofitHelper
import com.example.exampleretrofit.data.model.DogsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogsService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getDogByBreed(query: String): DogsResponse? = withContext(Dispatchers.IO) {
        val response = retrofit
            .create(DogsApiService::class.java)
            .getDogsByBreeds("$query/images")
        response.body()
    }
}