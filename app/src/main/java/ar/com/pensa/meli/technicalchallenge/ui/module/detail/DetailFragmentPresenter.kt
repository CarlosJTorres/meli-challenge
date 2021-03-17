package ar.com.pensa.meli.technicalchallenge.ui.module.detail

import android.util.Log
import ar.com.pensa.meli.technicalchallenge.business.model.ProductListItemBusinessModel
import ar.com.pensa.meli.technicalchallenge.business.model.product.ProductDescriptionBusinessModel
import ar.com.pensa.meli.technicalchallenge.data.backend.BackendManager
import ar.com.pensa.meli.technicalchallenge.data.backend.response.product.ProductDetailResponse
import ar.com.pensa.meli.technicalchallenge.data.backend.response.product.ProductDescriptionResponse
import ar.com.pensa.meli.technicalchallenge.ui.BaseFragmentPresenter
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductDescriptionUIModel
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductListItemUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * El presenter para la pantalla de detalle de producto.
 * Contiene una instancia
 * @see DetailFragmentView (una interfaz) que se implementa en la capa de UI (fragment).
 * Avisa cuando se obtienen los datos o cuando no se obtienen (...)
 */
class DetailFragmentPresenter(view: DetailFragmentView) :
    BaseFragmentPresenter<DetailFragmentView>(view) {

    /**
     * Para obtener el detalle del producto según su ID
     */
    fun getProductDetails(productID: String) {

        with(BackendManager) {
            with(meliService) {
                getProductDetails(productID)
                    .enqueue(object : Callback<ProductDetailResponse> {
                        override fun onFailure(call: Call<ProductDetailResponse>?, t: Throwable?) {
                            t?.localizedMessage?.let { view?.onError(it) }
                        }

                        override fun onResponse(
                            call: Call<ProductDetailResponse>?,
                            response: Response<ProductDetailResponse>?
                        ) {
                            if (response != null) {
                                if (response.isSuccessful) {
                                    if (response.body() != null && response.body()
                                            .getProduct() != null
                                    ) {

                                        //--

                                        val uiModel =
                                            ProductListItemUIModel(
                                                ProductListItemBusinessModel(
                                                    response.body()!!.getProduct()!!
                                                )
                                            )
                                        //-

                                        //-
                                        view?.onGetProductDetails(
                                            uiModel,                      //<-- Modelo Completo
                                            uiModel.productPictureList
                                        )   //<-- Solo Description
                                    } else {
                                        Log.w("torrancio", response.message() ?: "Error")
                                        view?.onError(response.message() ?: "Error")
                                    }
                                } else {
                                    Log.w("torrancio", response.message() ?: "Error!")
                                    view?.onError(response.message() ?: "Error!")
                                }
                            } else {
                                Log.w("torrancio", "Error!!")
                                view?.onError("Error!!")
                            }

                        }
                    })
            }
        }
    }

    /**
     * Para obtener la descripción del producto según su ID
     */
    fun getProductDescription(productID: String) {

        with(BackendManager) {
            with(meliService) {
                getProductDescriptionByID(productID)
                    .enqueue(object : Callback<ProductDescriptionResponse> {
                        override fun onFailure(
                            call: Call<ProductDescriptionResponse>?,
                            t: Throwable?
                        ) {
                            t?.localizedMessage?.let { view?.onError(it) }
                        }

                        override fun onResponse(
                            call: Call<ProductDescriptionResponse>?,
                            response: Response<ProductDescriptionResponse>?
                        ) {
                            if (response != null) {
                                if (response.isSuccessful) {
                                    if (response.body() != null) {

                                        //--
                                        val productDescriptionUIModel =
                                            ProductDescriptionUIModel(
                                                ProductDescriptionBusinessModel(
                                                    response.body().text,
                                                    response.body().plain_text
                                                )
                                            )
                                        //-
                                        view?.onGetDescription(
                                            productDescriptionUIModel,               //<-- Modelo Completo
                                            productDescriptionUIModel.description
                                        )   //<-- Solo Description
                                    } else {
                                        view?.onError(response.message() ?: "Error")
                                    }
                                } else {
                                    view?.onError(response.message() ?: "Error!")
                                }
                            } else {
                                view?.onError("Error!!")
                            }

                        }
                    })
            }
        }
    }


    /**
     * Nota: La idea de separar los distintos modelos UI/BUSINESS/BACKEND
     * es para continuar con una arquitectura limpia.
     * Siendo cada modelo perteneciente a su propia capa.
     * En una implementación extendida el presenter solo recibiria un BusinessModel
     * y le entregaría a la UI (fragment) un UIModel. (Como ocurre acá).
     *
     * La instancia de BackendModel es agena al presenter.
     */

}
