package ar.com.pensa.meli.technicalchallenge.business.model.product

import ar.com.pensa.meli.technicalchallenge.data.backend.model.product.ProductPictureBackendModel

open class ProductPictureBusinessModel(
    var id: String,
    var url: String? = "",
    var secure_url: String? = "",
    var size: String? = "",
    var max_size: String? = "",
    var quality: String? = ""
) {


    constructor() : this("", "", "", "", "")

    constructor(backendModel: ProductPictureBackendModel)
            : this("", "", "", "", "") {

        this.id = backendModel.id
        this.url = backendModel.id
            ?: "https://http2.mlstatic.com/resources/frontend/statics/img-not-available/1.1.0/R.gif"
        this.secure_url = backendModel.secure_url
            ?: "https://http2.mlstatic.com/resources/frontend/statics/img-not-available/1.1.0/R.gif"
        this.size = backendModel.size ?: ""
        this.max_size = backendModel.max_size ?: ""
        this.quality = backendModel.quality ?: ""
    }
}