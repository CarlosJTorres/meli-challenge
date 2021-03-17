package ar.com.pensa.meli.technicalchallenge.data.backend.model

//-- Objeto modelo de backend para paginación
data class PagingBackendModel(
    val total: Int? = -1,
    val primary_results: Int? = -1,
    val offset: Int? = -1,
    val limit: Int? = -1
) {
}