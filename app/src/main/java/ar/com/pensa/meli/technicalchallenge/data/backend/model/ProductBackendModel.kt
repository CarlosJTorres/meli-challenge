package ar.com.pensa.meli.technicalchallenge.data.backend.model

//EL item producto resultado del servicio de búsqueda
class ProductBackendModel(
    id: String,
    val title: String?,
    val price: Float? = 0F,
    val currency_id: String?,
    val thumbnail: String?,
    val condition: String?,
    val permalink: String?
) : ItemBaseBackendModel(id)