package ar.com.pensa.meli.technicalchallenge.ui.module.detail

import ar.com.pensa.meli.technicalchallenge.ui.BaseFragmentView
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductDescriptionUIModel
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductListItemUIModel
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductPictureUIModel

/**
 * Interfaz que permite al presenter enviar el los datos obtenidos a la UI/Fragment.
 */
interface DetailFragmentView : BaseFragmentView {
    fun onGetDescription(detailUIModel: ProductDescriptionUIModel, description: String)
    fun onGetProductDetails(
        detailUIModel: ProductListItemUIModel,
        picturesList: MutableList<ProductPictureUIModel>
    )
}