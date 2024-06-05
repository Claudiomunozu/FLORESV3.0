package com.example.floresv30.model.remote.retrofit

import com.example.floresv30.model.remote.response.FlowerDetailResponse
import com.example.floresv30.model.remote.response.FlowerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiFlower {

    @GET("plantas")
    suspend fun fetchFlowerList(): Response<List<FlowerResponse>>

    @GET("plantas/{id}")
    suspend fun fetchFlowerDetail(@Path("id") id: Int): Response<FlowerDetailResponse>

}