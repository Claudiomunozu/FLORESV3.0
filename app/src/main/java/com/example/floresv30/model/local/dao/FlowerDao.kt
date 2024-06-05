package com.example.floresv30.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.floresv30.model.local.entities.FlowerDetailLocal
import com.example.floresv30.model.local.entities.FlowerLocal
@Dao
interface FlowerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlowers(flowerList: List<FlowerLocal>)

    @Query("SELECT * FROM flower_table ORDER BY id ASC")
    fun getFlowers(): LiveData<List<FlowerLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailFlower(flowerDetails: FlowerDetailLocal)

    @Query("SELECT * FROM flower_detail WHERE id = :id")
    fun getDetailFlowerById(id: Int): LiveData<FlowerDetailLocal>
}
