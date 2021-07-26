package co.ruizhang.cruddemo.di

import co.ruizhang.cruddemo.data.RepoAPI
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
}


private fun provideRetrofit(): RepoAPI {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(RepoAPI::class.java)
}