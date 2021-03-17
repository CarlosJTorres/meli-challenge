package ar.com.pensa.meli.technicalchallenge.business.model

import ar.com.pensa.meli.technicalchallenge.data.backend.model.PagingBackendModel

data class PagingBusinessModel(
    var total: Int? = -1,
    var primary_results: Int? = -1,
    var offset: Int? = -1,
    var limit: Int? = -1
) {

    constructor(pagingBackendModel: PagingBackendModel?) : this(-1, -1, -1, 1) {
        if (pagingBackendModel != null) {
            this.total = pagingBackendModel.total ?: -1
            this.primary_results = pagingBackendModel.primary_results ?: -1
            this.offset = pagingBackendModel.offset ?: -1
            this.limit = pagingBackendModel.limit ?: -1
        } else {
            this.total = -1
            this.primary_results = -1
            this.offset = -1
            this.limit = -1
        }
    }

    constructor() : this(-1, -1, -1, -1) {
        this.total = -1
        this.primary_results = -1
        this.offset = -1
        this.limit = -1
    }
}