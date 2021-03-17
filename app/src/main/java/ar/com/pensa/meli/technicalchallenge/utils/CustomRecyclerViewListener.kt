package ar.com.pensa.meli.technicalchallenge.utils

import android.view.View

/**
 * Listener genérico para RecyclerViews que permite recibir un ITEM T al clickear, no solo el View.
 */
interface CustomRecyclerViewListener<T> {
    fun onItemClicked(item: T, itemView: View, position: Int)
}