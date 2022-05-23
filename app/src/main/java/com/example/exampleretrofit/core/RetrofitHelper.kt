package com.example.exampleretrofit.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url = "https://dog.ceo/api/breed/"

object RetrofitHelper {
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}