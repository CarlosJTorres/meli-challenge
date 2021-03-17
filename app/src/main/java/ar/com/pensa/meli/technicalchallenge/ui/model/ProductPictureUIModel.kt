package ar.com.pensa.meli.technicalchallenge.ui.model

import ar.com.pensa.meli.technicalchallenge.business.model.product.ProductPictureBusinessModel

/**
 * Modelo de UI que contiene la información de la foto del producto.
 * Usualmente viene listado con otros.
 */
open class ProductPictureUIModel(
    var id: String,
    var url: String? = "",
    var secure_url: String,
    var size: String? = "",
    var max_size: String? = "",
    var quality: String? = ""
) {


    constructor() : this("", "", "", "", "")

    constructor(businessModel: ProductPictureBusinessModel)
            : this("", "", "", "", "") {

        this.id = businessModel.id
        this.url = businessModel.id
            ?: "https://http2.mlstatic.com/resources/frontend/statics/img-not-available/1.1.0/R.gif"
        this.secure_url = businessModel.secure_url
            ?: "https://http2.mlstatic.com/resources/frontend/statics/img-not-available/1.1.0/R.gif"
        this.size = businessModel.size ?: ""
        this.max_size = businessModel.max_size ?: ""
        this.quality = businessModel.quality ?: ""
    }
}