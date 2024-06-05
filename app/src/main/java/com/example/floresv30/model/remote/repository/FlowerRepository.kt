package com.example.floresv30.model.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.floresv30.model.local.dao.FlowerDao
import com.example.floresv30.model.local.entities.FlowerDetailLocal
import com.example.floresv30.model.remote.mapper.fromInternetDetails
import com.example.floresv30.model.remote.mapper.fromInternetList
import com.example.floresv30.model.remote.retrofit.RetrofitFlower

class FlowerRepository (private val flowerDao: FlowerDao) {

    private val networkService = RetrofitFlower.retrofitInstance()

    val flowerListLiveData = flowerDao.getFlowers()


    suspend fun fetchList() {
        val service = kotlin.runCatching { networkService.fetchFlowerList() }

        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {

                    Log.d("Flowers", it.toString())

                    flowerDao.insertFlowers(fromInternetList(it))

                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }

        }

    }


    suspend fun fetchFlowerDetails(id: Int): FlowerDetailLocal? {

        val service = kotlin.runCatching { networkService.fetchFlowerDetail(id) }

        return service.getOrNull()?.body()?.let { Details ->

            val flowersDetails = fromInternetDetails(Details)

            flowerDao.insertDetailFlower(flowersDetails)
            flowersDetails
        }
    }

    fun getFlowersDetailsById(id: Int): LiveData<FlowerDetailLocal> {
        return flowerDao.getDetailFlowerById(id)
    }

}