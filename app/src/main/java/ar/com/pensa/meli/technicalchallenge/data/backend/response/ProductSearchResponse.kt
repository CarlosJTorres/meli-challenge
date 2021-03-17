package ar.com.pensa.meli.technicalchallenge.data.backend.response

import ar.com.pensa.meli.technicalchallenge.data.backend.model.PagingBackendModel
import ar.com.pensa.meli.technicalchallenge.data.backend.model.ProductBackendModel
import ar.com.pensa.meli.technicalchallenge.data.backend.response.BaseResponse

/**
 * Objeto para respuesta de servicio de búsqueda, incluye heredadamente la páginación
 */
class ProductSearchResponse(
    paging: PagingBackendModel?,
    var results: MutableList<ProductBackendModel>? = mutableListOf()
) : BaseResponse(paging) {
}