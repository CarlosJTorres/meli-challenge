package ar.com.pensa.meli.technicalchallenge.business.model

import ar.com.pensa.meli.technicalchallenge.data.backend.model.PagingBackendModel
import ar.com.pensa.meli.technicalchallenge.data.backend.model.ProductSearchBackendModel

class ProductSearchBusinessModel(
    var pagingBusinessModel: PagingBusinessModel? = null,
    var productListBusinessModel: MutableList<ProductBusinessModel>
) {

    constructor(productSearchBackendModel: ProductSearchBackendModel)
            : this(null, mutableListOf()) {

        pagingBusinessModel =
            PagingBusinessModel(productSearchBackendModel.paging ?: PagingBackendModel())

        //-
        productListBusinessModel = mutableListOf()
        productSearchBackendModel.results?.forEach {
            productListBusinessModel.plus(ProductBusinessModel(it))
        }

    }
}