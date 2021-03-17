package ar.com.pensa.meli.technicalchallenge.data.backend.response

import ar.com.pensa.meli.technicalchallenge.data.backend.model.ItemBaseBackendModel

//Clase genérica. Item para las respuestas tipo lista que contienen http code y body (que es un Item genérico con ID)
data class ListItemBaseResponse<ItemResponseModel : ItemBaseBackendModel>(
    val code: Int,
    val body: ItemResponseModel
)