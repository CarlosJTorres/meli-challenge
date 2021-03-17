package ar.com.pensa.meli.technicalchallenge.data.backend.response.product

import ar.com.pensa.meli.technicalchallenge.data.backend.model.ProductListItemBackendModel
import ar.com.pensa.meli.technicalchallenge.data.backend.response.ProductListItemResponse

/**
 * Clase de datos para la respuesta del detalle de un producto.(LISTA)
 * El servicio responde una lista de elementos, en este caso necesitamos solo uno, y pedimos solo uno.
 */
class ProductDetailResponse() : ArrayList<ProductListItemResponse>() {

    /**
     * Para obtener el único elemento de la lista.
     */
    fun getProduct(): ProductListItemBackendModel? {

        return if (isNotEmpty()) {
            get(0).body
        } else {
            null
        }

    }
}