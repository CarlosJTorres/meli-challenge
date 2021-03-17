package ar.com.pensa.meli.technicalchallenge.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.viewpager.widget.ViewPager
import ar.com.pensa.meli.technicalchallenge.R

/**
 * Widget o CustomView que funciona como Slider
 * para las imágenes del producto.
 */
class ImageSliderView(context: Context?, attributeSet: AttributeSet?) :
    LinearLayout(context, attributeSet) {
    // creating object of ViewPager
    private lateinit var mViewPager: ViewPager
    private lateinit var progressBar: ProgressBar

    // Creating Object of ViewPagerAdapter
    var mViewPagerAdapter: ViewPagerAdapter? = null

    var images = mutableListOf<String>()


    constructor(context: Context?, attributeSet: AttributeSet, defStyleAttr: Int) : this(
        context,
        attributeSet
    )


    init {
        //val layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        Log.d("torrancio", "init en constructor")
        View.inflate(context, R.layout.view_image_slider, this)


    }

    fun set(imagesURL: MutableList<String>) {
        this.images = imagesURL

        mViewPager = findViewById(R.id.item_image_slider_vp)
        progressBar = findViewById(R.id.item_image_slider_pb_loading)

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = ViewPagerAdapter(context, images)


        // Adding the Adapter to the ViewPager
        mViewPager!!.adapter = mViewPagerAdapter

        progressBar.visibility = View.GONE

        //-----


    }


}