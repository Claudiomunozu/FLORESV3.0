package com.example.floresv30.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.floresv30.model.local.database.FlowerDatabase
import com.example.floresv30.model.local.entities.FlowerDetailLocal
import com.example.floresv30.model.local.entities.FlowerLocal
import com.example.floresv30.model.remote.repository.FlowerRepository
import kotlinx.coroutines.launch

class FlowerViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: FlowerRepository
    private var idSelected: Int = 0

    init {
        val bd = FlowerDatabase.getDataBase(application)
        val Dao = bd.getFlowerDao()
        repository = FlowerRepository(Dao)
        viewModelScope.launch {
            repository.fetchList()
        }
    }

    fun getFlowersList(): LiveData<List<FlowerLocal>> = repository.flowerListLiveData

    fun getFlowersDetailsByIdFromInternet(id: Int) = viewModelScope.launch {
        idSelected = id
        repository.fetchFlowerDetails(id)

    }
    fun getFlowersDetail(): LiveData<FlowerDetailLocal> = repository.getFlowersDetailsById(idSelected)
}