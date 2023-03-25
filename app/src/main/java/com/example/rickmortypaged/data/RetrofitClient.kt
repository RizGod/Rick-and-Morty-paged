package com.example.rickmortypaged.data

import com.example.rickmortypaged.data.detailed_information.Episode
import com.example.rickmortypaged.data.main_character_list.PagedCharacterList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com"

interface RetrofitClient {


    @GET("/api/character/")
    suspend fun characterList(@Query("page") page: Int): PagedCharacterList

    @GET("/api/episode/{episode}")
    suspend fun episodeInfo(@Path("episode") episode: Int): Episode
}

val retrofit: RetrofitClient = Retrofit
    .Builder()
    .client(
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()
    )
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
    .create(RetrofitClient::class.java)