package ar.com.pensa.meli.technicalchallenge.ui.model

import ar.com.pensa.meli.technicalchallenge.business.model.ProductBusinessModel
import ar.com.pensa.meli.technicalchallenge.utils.AppUtils

/**
 * Modelo de UI para Producto,
 * es el resultado de hacer un Search al API (luego de pasar la capa de Bussiness y Backend)
 */
data class ProductUIModel(
    var id: String,
    var title: String?,
    var price: String,
    var thumbnail: String,
    var condition: String,
    var permalink: String
) {

    constructor(productBusinessModel: ProductBusinessModel) : this("", "", "", "", "", "") {
        id = if (productBusinessModel.id.isNotEmpty()) productBusinessModel.id else "NO_ID"
        title =
            if (productBusinessModel.title.isNotEmpty()) productBusinessModel.title else "Sin título"
        price = "$ ".plus(AppUtils.formatNumber(productBusinessModel.price))
        thumbnail = productBusinessModel.thumbnail
        condition = productBusinessModel.condition
        permalink = productBusinessModel.permalink

    }
}