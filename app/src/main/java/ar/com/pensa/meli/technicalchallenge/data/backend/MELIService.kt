package ar.com.pensa.meli.technicalchallenge.data.backend

import ar.com.pensa.meli.technicalchallenge.data.backend.response.product.ProductDetailResponse
import ar.com.pensa.meli.technicalchallenge.data.backend.response.ProductSearchResponse
import ar.com.pensa.meli.technicalchallenge.data.backend.response.product.ProductDescriptionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MELIService {

    /**
     * Servicio para buscar productos por texto
     */
    @GET("/sites/MLA/search")
    fun getProductBySearchText(
        @Query("q") textToSearch: String?,
        @Query("limit") pageSize: Int,
        @Query("offset") pageNumber: Int
    ): Call<ProductSearchResponse>

    /**
     * Servicio para obtener descripción de producto por el ID
     */
    @GET("/items/{item_id}/description")
    fun getProductDescriptionByID(
        @Path("item_id") productID: String
    ): Call<ProductDescriptionResponse>

    /**
     * Servicio para obtener todos los detalles de un prodcuto según su ID
     */
    @GET("/items")
    fun getProductDetails(
        @Query("ids") productID: String
    ): Call<ProductDetailResponse>

}