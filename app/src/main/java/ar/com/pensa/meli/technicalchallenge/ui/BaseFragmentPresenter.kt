package ar.com.pensa.meli.technicalchallenge.ui

/**
 * Presenter base genérico para otros extener a otros presenter.
 * Los hijos tendría deben contener un FragmentView
 * que luego sería usado para comunicarse con el activity/fragment/ui
 */
open class BaseFragmentPresenter<FragmentView : BaseFragmentView?>(var view: FragmentView?) {

}