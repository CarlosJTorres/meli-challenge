package ar.com.pensa.meli.technicalchallenge.business.model

import ar.com.pensa.meli.technicalchallenge.business.model.product.ProductPictureBusinessModel
import ar.com.pensa.meli.technicalchallenge.data.backend.model.ProductListItemBackendModel

data class ProductListItemBusinessModel(
    var title: String,
    var price: Float,
    var currency_id: String,
    var condition: String,
    var permalink: String,
    var productPictureList: MutableList<ProductPictureBusinessModel>?
) {

    constructor()
            : this("", 0F, "", "new", "", mutableListOf())

    constructor(backendModel: ProductListItemBackendModel) :
            this(",", 0F, "", "", "", null) {
        this.title = backendModel.title ?: "Sin titulo"
        this.price = backendModel.price ?: 0F
        this.currency_id = backendModel.currency_id ?: "ARS"
        this.condition = backendModel.condition ?: "new"
        this.permalink = backendModel.permalink ?: ""

        this.productPictureList = mutableListOf()

        if (backendModel.pictures != null) {
            backendModel.pictures!!.forEach {
                this.productPictureList!!.add(
                    ProductPictureBusinessModel(it)
                )
            }
        }


    }
}