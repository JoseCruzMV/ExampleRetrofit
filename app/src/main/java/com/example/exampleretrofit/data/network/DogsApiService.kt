package com.example.exampleretrofit.data.network

import com.example.exampleretrofit.data.model.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogsApiService {
    @GET
    suspend fun getDogsByBreeds(@Url url:String): Response<DogsResponse>
}