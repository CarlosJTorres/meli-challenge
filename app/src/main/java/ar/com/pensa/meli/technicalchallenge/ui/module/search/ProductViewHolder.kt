package ar.com.pensa.meli.technicalchallenge.ui.module.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.pensa.meli.technicalchallenge.R

/**
 * ViewHolder, permite instanciar/representar la un item view para reutilizar en el adapter.
 */
class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView? = null
    var price: TextView? = null
    var image: ImageView


    init {
        title = itemView.findViewById(R.id.list_item_product_tv_product_title)
        price = itemView.findViewById(R.id.list_item_product_tv_product_price)
        image = itemView.findViewById(R.id.list_item_product_iv)
    }
}