package ar.com.pensa.meli.technicalchallenge.business.model

import ar.com.pensa.meli.technicalchallenge.data.backend.model.ProductBackendModel

data class ProductBusinessModel(
    var id: String,
    var title: String,
    var price: Float,
    var currency_id: String,
    var thumbnail: String,
    var condition: String,
    var permalink: String
) {

    constructor(productBackendModel: ProductBackendModel)
            : this("", "", 0F, "", "", "", "") {
        this.id = productBackendModel.id ?: ""
        this.title = productBackendModel.title ?: "Sin titulo"
        this.price = productBackendModel.price ?: 0F
        this.currency_id = productBackendModel.currency_id ?: "ARS"
        this.thumbnail = productBackendModel.thumbnail ?: ""
        this.condition = productBackendModel.condition ?: "new?"
        this.permalink = productBackendModel.permalink ?: "https://www.mercadolibre.com/"

        //--
        this.thumbnail = this.thumbnail.replace("http://", "https://")
    }

}