package ar.com.pensa.meli.technicalchallenge.data.backend.response

import ar.com.pensa.meli.technicalchallenge.data.backend.model.PagingBackendModel

//Base Response para todas las respuetas, incluye Paging opcional
open class BaseResponse(var paging: PagingBackendModel? = null)