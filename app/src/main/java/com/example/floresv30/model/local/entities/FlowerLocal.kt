package com.example.floresv30.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flower_table")
data class FlowerLocal (
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String

)