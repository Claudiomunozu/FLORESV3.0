package com.example.floresv30.model.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFlower {

    companion object{

        private const val BASE_URL ="https://my-json-server.typicode.com/mauricioponce/TDApi/"

        lateinit var retrofitInstance: Retrofit

        fun retrofitInstance(): ApiFlower{
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return retrofit.create(ApiFlower::class.java)
        }
    }
}