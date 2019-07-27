package com.ronaldo.coelho.garcia.pokemonpo

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Client {

    private const val BASE_URL = "https://gamestjd.herokuapp.com"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor{
        chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .method(original.method(), original.body())
        val request = requestBuilder.build()
        chain.proceed(request)

    }.build()

    val instance: Service by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Service::class.java)
    }
}