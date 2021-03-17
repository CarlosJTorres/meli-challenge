package ar.com.pensa.meli.technicalchallenge.business.model.product

import ar.com.pensa.meli.technicalchallenge.data.backend.model.product.ProductDescriptionBackendModel

data class ProductDescriptionBusinessModel(
    var text: String? = "", var plain_text: String? = ""
) {

    constructor(backendModel: ProductDescriptionBackendModel) : this("", "") {
        text = backendModel.text ?: ""
        plain_text = backendModel.text_plain ?: ""
    }
}