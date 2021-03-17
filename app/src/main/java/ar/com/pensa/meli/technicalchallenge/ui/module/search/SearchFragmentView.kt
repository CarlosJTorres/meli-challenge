package ar.com.pensa.meli.technicalchallenge.ui.module.search

import ar.com.pensa.meli.technicalchallenge.ui.BaseFragmentView
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductUIModel

interface SearchFragmentView : BaseFragmentView {
    fun onGetProducts(productUIModelsList: MutableList<ProductUIModel>, results: Int)
}