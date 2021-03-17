package ar.com.pensa.meli.technicalchallenge.data.backend.response

import ar.com.pensa.meli.technicalchallenge.data.backend.model.ProductListItemBackendModel

/**
 * Es el elemento de respuesta del API al momento de obtener el Detalle de un producto.
 * Usualmente viene en un lista, necesitamos solo un elemento.
 */
open class ProductListItemResponse(var code: Int, var body: ProductListItemBackendModel)