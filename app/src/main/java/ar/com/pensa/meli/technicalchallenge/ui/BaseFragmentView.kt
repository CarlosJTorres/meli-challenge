package ar.com.pensa.meli.technicalchallenge.ui

interface BaseFragmentView {
    fun onLoading()
    fun onError(msg: String)
}