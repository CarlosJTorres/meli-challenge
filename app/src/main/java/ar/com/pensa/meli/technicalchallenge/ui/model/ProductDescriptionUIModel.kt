package ar.com.pensa.meli.technicalchallenge.ui.model

import ar.com.pensa.meli.technicalchallenge.business.model.product.ProductDescriptionBusinessModel

/**
 * UI Model para la descripción del producto. Los UI model solo deberiamos usarlos en la capa de UI
 */
class ProductDescriptionUIModel(var description: String) {

    constructor(businessModel: ProductDescriptionBusinessModel) : this("") {
        description =
            businessModel.plain_text ?: businessModel.text
                    ?: "Sin descripción" //<-- Usa plain_text, sino text, sino "Sin descripción"
    }
}