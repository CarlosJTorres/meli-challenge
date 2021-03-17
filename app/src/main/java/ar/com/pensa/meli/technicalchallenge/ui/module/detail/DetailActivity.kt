package ar.com.pensa.meli.technicalchallenge.ui.module.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.com.pensa.meli.technicalchallenge.R

/**
 * Pantalla de Detalle del Producto.
 */
class DetailActivity : AppCompatActivity() {

    companion object {

        const val ITEM_PARAM_ID = "ITEM_PARAM_ID"
        const val ITEM_PARAM_TITLE = "ITEM_PARAM_TITLE"
        const val ITEM_PARAM_PRICE = "ITEM_PARAM_PRICE"
        const val ITEM_PARAM_IMAGE = "ITEM_PARAM_IMAGE"
        const val ITEM_PARAM_CONDITION = "ITEM_PARAM_CONDITION"
        const val ITEM_PARAM_PERMALINK = "ITEM_PARAM_PERMALINK"

        fun getCallingIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }

        /**
         * Parte de la información del producto la traigo desde la pantalla de búsqueda
         */
        fun getCallingIntent(
            context: Context,
            id: String,
            title: String,
            price: String,
            image: String,
            condition: String?,
            permalink: String
        )
                : Intent {

            return Intent(context, DetailActivity::class.java).apply {
                putExtra(ITEM_PARAM_ID, id)
                putExtra(ITEM_PARAM_TITLE, title)
                putExtra(ITEM_PARAM_PRICE, price)
                putExtra(ITEM_PARAM_IMAGE, image)
                putExtra(ITEM_PARAM_CONDITION, condition ?: "new")
                putExtra(ITEM_PARAM_PERMALINK, permalink)
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (savedInstanceState == null) {
            val productId = intent.getStringExtra(ITEM_PARAM_ID) ?: ""
            val title = intent.getStringExtra(ITEM_PARAM_TITLE) ?: "No title"
            val price = intent.getStringExtra(ITEM_PARAM_PRICE) ?: "0"
            val image = intent.getStringExtra(ITEM_PARAM_IMAGE) ?: ""
            val condition = intent.getStringExtra(ITEM_PARAM_CONDITION) ?: "new"
            val permalink = intent.getStringExtra(ITEM_PARAM_PERMALINK) ?: ""

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    DetailFragment.newInstance(productId, title, price, image, condition, permalink)
                )
                .commitNow()
        }
    }
}