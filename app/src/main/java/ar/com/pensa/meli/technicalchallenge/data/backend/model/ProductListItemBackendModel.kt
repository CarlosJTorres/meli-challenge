package ar.com.pensa.meli.technicalchallenge.data.backend.model

import ar.com.pensa.meli.technicalchallenge.data.backend.model.product.ProductPictureBackendModel

//Product Item, resultado de obtener UNO o varios Product Items
class ProductListItemBackendModel(
    id: String,
    var title: String?,
    var price: Float?,
    var currency_id: String?,
    var condition: String?,
    var permalink: String?,
    var pictures: MutableList<ProductPictureBackendModel>?
) : ItemBaseBackendModel(id)