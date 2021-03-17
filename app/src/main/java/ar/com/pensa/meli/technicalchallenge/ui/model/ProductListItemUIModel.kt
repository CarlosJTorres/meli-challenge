package ar.com.pensa.meli.technicalchallenge.ui.model

import ar.com.pensa.meli.technicalchallenge.business.model.ProductListItemBusinessModel
import ar.com.pensa.meli.technicalchallenge.utils.AppUtils

/**
 * Modelo de UI para Producto,
 * es el resultado de obtener el detalle de un producto
 */
data class ProductListItemUIModel(
    var title: String,
    var price: String,
    var currency_id: String,
    var condition: String,
    var permalink: String,
    var productPictureList: MutableList<ProductPictureUIModel>
) {

    constructor(businessModel: ProductListItemBusinessModel) :
            this(",", "", "", "", "", mutableListOf()) {
        this.title = businessModel.title
        this.price = "$ ".plus(AppUtils.formatNumber(businessModel.price))
        this.currency_id = businessModel.currency_id
        this.condition = businessModel.condition
        this.permalink = businessModel.permalink

        this.productPictureList = mutableListOf()

        if (businessModel.productPictureList != null) {
            businessModel.productPictureList!!.forEach {
                this.productPictureList!!.add(
                    ProductPictureUIModel(it)
                )
            }
        }
    }
}