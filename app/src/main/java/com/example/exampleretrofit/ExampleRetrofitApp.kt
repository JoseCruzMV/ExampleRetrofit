package com.example.exampleretrofit

import android.app.Application
import com.example.exampleretrofit.data.DogRepository
import com.example.exampleretrofit.data.network.DogsApiService
import com.example.exampleretrofit.data.network.DogsService
import com.example.exampleretrofit.domain.GetDogByBreedUseCase
import com.example.exampleretrofit.ui.viewmodel.DogsByBreedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url = "https://dog.ceo/api/breed/"

class ExampleRetrofitApp : Application() {
    private val koinModule = module {
        single { provideRetrofit() }
        single { provideDogApi(get()) }
        single { DogsService(get()) }
        single { DogRepository(get()) }
        single { GetDogByBreedUseCase(get()) }

        viewModel { DogsByBreedViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ExampleRetrofitApp)
            modules(koinModule)
        }
    }

    private fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun provideDogApi(retrofit: Retrofit): DogsApiService =
        retrofit.create(DogsApiService::class.java)
}