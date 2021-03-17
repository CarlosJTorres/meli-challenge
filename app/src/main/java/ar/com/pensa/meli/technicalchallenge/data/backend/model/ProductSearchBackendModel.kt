package ar.com.pensa.meli.technicalchallenge.data.backend.model

class ProductSearchBackendModel(
    var paging: PagingBackendModel? = null,
    var results: List<ProductBackendModel>? = ArrayList()
) {
}