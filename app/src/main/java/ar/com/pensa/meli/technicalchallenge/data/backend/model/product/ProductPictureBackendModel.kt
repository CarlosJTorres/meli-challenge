package ar.com.pensa.meli.technicalchallenge.data.backend.model.product

import ar.com.pensa.meli.technicalchallenge.data.backend.model.ItemBaseBackendModel

open class ProductPictureBackendModel(
    id: String,
    var url: String? = "",
    var secure_url: String? = "",
    var size: String? = "",
    var max_size: String? = "",
    var quality: String? = ""
) : ItemBaseBackendModel(id) {
}