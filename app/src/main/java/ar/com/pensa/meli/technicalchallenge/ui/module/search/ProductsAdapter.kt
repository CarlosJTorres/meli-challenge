package ar.com.pensa.meli.technicalchallenge.ui.module.search

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.pensa.meli.technicalchallenge.R
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductUIModel
import ar.com.pensa.meli.technicalchallenge.utils.CustomRecyclerViewListener
import com.bumptech.glide.Glide

/**
 * Adapter de productos buscados y listados
 */
class ProductsAdapter(var listener: CustomRecyclerViewListener<ProductUIModel>? = null) :
    RecyclerView.Adapter<ProductViewHolder>() {

    private var productsList: MutableList<ProductUIModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (this.productsList == null) 0 else this.productsList.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        //-
        val productUIModel = productsList[position]
        holder.title!!.text = productUIModel.title
        holder.price!!.text = productUIModel.price

        Log.d("torrancio", "imagen-->".plus(productUIModel.thumbnail))
        //-
        Glide
            .with(holder.itemView.context)
            .load(productUIModel.thumbnail)
            .into(holder.image)



        holder.itemView.setOnClickListener(
            View.OnClickListener { v ->
                if (listener != null) {
                    listener!!.onItemClicked(
                        productUIModel,
                        v,
                        productsList.indexOf(productUIModel)
                    )
                }
            })

        Log.d("torrancio", "torrancio:$position")

    }

    fun insertItems(items: MutableList<ProductUIModel>) {
        this.productsList.addAll(items)

        notifyDataSetChanged()

    }

    fun removeItems() {
        this.productsList.clear()
        notifyDataSetChanged()
    }
}