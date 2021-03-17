package ar.com.pensa.meli.technicalchallenge.ui.module.search

import android.util.Log
import ar.com.pensa.meli.technicalchallenge.business.model.ProductBusinessModel
import ar.com.pensa.meli.technicalchallenge.data.backend.BackendManager
import ar.com.pensa.meli.technicalchallenge.data.backend.response.ProductSearchResponse
import ar.com.pensa.meli.technicalchallenge.ui.BaseFragmentPresenter
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * El presenter para la pantalla de búsqueda de productos.
 * Contiene una instancia
 * @see SearchFragmentView (una interfaz) que se implementa en la capa de UI (fragment).
 * Avisa cuando se obtienen los datos o cuando no se obtienen (...)
 */
class SearchFragmentPresenter(view: SearchFragmentView) :
    BaseFragmentPresenter<SearchFragmentView>(view) {


    /**
     * Obtiene los productos buscados
     */
    fun getProducts(textToSearch: String, pageNumber: Int) {
        view?.onLoading()

        with(BackendManager) {
            with(meliService) {
                getProductBySearchText(
                    textToSearch,
                    10,
                    pageNumber
                )
                    .enqueue(object : Callback<ProductSearchResponse> {
                        override fun onFailure(call: Call<ProductSearchResponse>?, t: Throwable?) {
                            view?.onError(t?.localizedMessage.toString())
                        }

                        override fun onResponse(
                            call: Call<ProductSearchResponse>?,
                            response: Response<ProductSearchResponse>?
                        ) {
                            if (response != null && response.isSuccessful) {
                                Log.d("torrancio", "isSuccessful")

                                //--
                                var productUIModelsList: MutableList<ProductUIModel> =
                                    mutableListOf()
                                var total: Int = -1

                                if (response.body() != null && response.body().results != null) {

                                    response.body().results!!.forEach {
                                        productUIModelsList
                                            .add(
                                                ProductUIModel(
                                                    ProductBusinessModel(
                                                        it
                                                    )
                                                )
                                            )
                                    }

                                    //-
                                    if (response.body().paging != null) {
                                        total = response.body().paging!!.total!!
                                    }

                                }

                                view?.onGetProducts(
                                    productUIModelsList,
                                    total
                                )  // <--- en envía a UI
                            } else {
                                view?.onGetProducts(mutableListOf(), 0)
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
