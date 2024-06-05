package com.example.floresv30.model.remote.mapper

import com.example.floresv30.model.local.entities.FlowerDetailLocal
import com.example.floresv30.model.local.entities.FlowerLocal
import com.example.floresv30.model.remote.response.FlowerDetailResponse
import com.example.floresv30.model.remote.response.FlowerResponse

fun fromInternetList(flowerList: List<FlowerResponse>): List<FlowerLocal> {

    return flowerList.map {
        FlowerLocal(
            id = it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion

        )
    }
}


fun fromInternetDetails(detailsFlower: FlowerDetailResponse): FlowerDetailLocal {

    return FlowerDetailLocal(
        id = detailsFlower.id,
        nombre = detailsFlower.nombre,
        tipo = detailsFlower.tipo,
        imagen = detailsFlower.imagen,
        descripcion = detailsFlower.descripcion
    )
}