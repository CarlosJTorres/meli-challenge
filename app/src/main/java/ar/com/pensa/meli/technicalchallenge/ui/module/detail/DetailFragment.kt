package ar.com.pensa.meli.technicalchallenge.ui.module.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ar.com.pensa.meli.technicalchallenge.R
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductDescriptionUIModel
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductListItemUIModel
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductPictureUIModel
import ar.com.pensa.meli.technicalchallenge.ui.widget.ImageSliderView
import com.bumptech.glide.Glide


/**
 * Fragment de Detalle, con implementación MVP
 */
class DetailFragment : Fragment(), DetailFragmentView {

    //- Variables
    private lateinit var id: String
    private lateinit var title: String
    private lateinit var price: String
    private lateinit var image: String
    private lateinit var condition: String
    private lateinit var permalink: String

    //-
    private var presenter: DetailFragmentPresenter? =
        null  //<---Equivalente vigente a ViewModel de MVVM

    //- Views

    private lateinit var tvCondition: TextView
    private lateinit var tvTitle: TextView
    private lateinit var ivImage: ImageView
    private lateinit var tvPrice: TextView
    private lateinit var bBuy: Button
    private lateinit var bToCar: Button
    private lateinit var tvDescription: TextView
    private lateinit var isvImages: ImageSliderView


    companion object {

        fun newInstance(
            productID: String,
            title: String,
            price: String,
            image: String,
            condition: String,
            permalink: String
        ) =
            DetailFragment().apply {
                arguments =
                    bundleOf(
                        Pair(DetailActivity.ITEM_PARAM_ID, productID),
                        Pair(DetailActivity.ITEM_PARAM_TITLE, title),
                        Pair(DetailActivity.ITEM_PARAM_PRICE, price),
                        Pair(DetailActivity.ITEM_PARAM_IMAGE, image),
                        Pair(DetailActivity.ITEM_PARAM_CONDITION, condition),
                        Pair(DetailActivity.ITEM_PARAM_PERMALINK, permalink)
                    )

            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            this.id = arguments!!.getString(DetailActivity.ITEM_PARAM_ID) ?: ""
            this.title = arguments!!.getString(DetailActivity.ITEM_PARAM_TITLE) ?: ""
            this.price = arguments!!.getString(DetailActivity.ITEM_PARAM_PRICE) ?: ""
            this.image = arguments!!.getString(DetailActivity.ITEM_PARAM_IMAGE) ?: ""
            this.condition = arguments!!.getString(DetailActivity.ITEM_PARAM_CONDITION) ?: ""
            this.permalink = arguments!!.getString(DetailActivity.ITEM_PARAM_PERMALINK) ?: ""
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //- View instantiation ------------
        tvCondition = view.findViewById(R.id.fragment_detail_tv_estado)
        tvTitle = view.findViewById(R.id.fragment_detail_tv_message)
        ivImage = view.findViewById(R.id.fragment_detail_iv_image)
        tvPrice = view.findViewById(R.id.fragment_detail_tv_price)
        bBuy = view.findViewById(R.id.fragment_detail_b_buy)
        bBuy = view.findViewById(R.id.fragment_detail_b_buy)
        bToCar = view.findViewById(R.id.fragment_detail_b_to_car)
        tvDescription = view.findViewById(R.id.fragment_detail_tv_description_value)
        isvImages = view.findViewById(R.id.fragment_detail_isv_images)

        //- Views configuration -----------
        tvCondition.text = if (condition == "new") "Nuevo" else "Usado" //<-- ToDo: mover a Strings
        tvTitle.text = title
        tvPrice.text = price

        //- ImageView --> Reemplazado por -IMAGE SLIDER VIEW (más abajo) | @ToDo: borrar esto
        Glide
            .with(this)
            .load(image)
            .into(ivImage)

        //-- Listeners -> Botones de Compra y Carrito
        bBuy.setOnClickListener {
            Intent(Intent.ACTION_VIEW).setData(Uri.parse(permalink)).apply {
                startActivity(this)
            }
        }

        bToCar.setOnClickListener {
            Intent(Intent.ACTION_VIEW).setData(Uri.parse(permalink)).apply {
                startActivity(this)
            }
        }


        //--IMAGE SLIDER VIEW

        //- Presenter
        if (presenter == null) {
            presenter = DetailFragmentPresenter(this)
        }

        //--- Se pide al presenter el detalle del producto(como las imagenes) y la descripción
        presenter!!.getProductDetails(id)
        presenter!!.getProductDescription(id)


    }

    /**
     * En este putno el presenter respondió con la descripción del producto
     */
    override fun onGetDescription(detailUIModel: ProductDescriptionUIModel, description: String) {
        tvDescription.text = description
    }

    /**
     * En este punto el presenter respondió los detalles del producto (como las imagenes para el slider)
     */
    override fun onGetProductDetails(
        detailUIModel: ProductListItemUIModel,
        picturesList: MutableList<ProductPictureUIModel>
    ) {

        val pictureURLList = mutableListOf<String>()
        picturesList.forEach {
            pictureURLList.add(it.secure_url)
        }

        isvImages.set(pictureURLList)
    }

    override fun onLoading() {
        //..
    }

    /**
     * En este punto el presenter falló en obtener algún dato.
     */
    override fun onError(msg: String) {
        Toast
            .makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            )
            .show()
    }

}