package ar.com.pensa.meli.technicalchallenge.ui.widget


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import ar.com.pensa.meli.technicalchallenge.R
import com.bumptech.glide.Glide

/**
 * Adapter para de imagenes.
 */
class ViewPagerAdapter() : PagerAdapter() {

    // Context object
    var context: Context? = null

    // Array of images
    private lateinit var images: MutableList<String>

    // Layout Inflater
    var mLayoutInflater: LayoutInflater? = null

    constructor(context: Context?, images: MutableList<String>) : this() {
        this.context = context
        this.images = images
        mLayoutInflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun getCount(): Int {
        // return the number of images
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        // inflating the item.xml
        val itemView: View =
            mLayoutInflater!!.inflate(R.layout.view_image_slider_container, container, false)

        // referencing the image view from the item.xml file
        val imageView: ImageView = itemView.findViewById<View>(R.id.imageViewMain) as ImageView

        // setting the image in the imageView

        //- ImageView
        Glide
            .with(context!!)
            .load(images[position])
            .into(imageView)

        // Adding the View

        // Adding the View

        itemView.setOnClickListener {
            Log.d("torrancio", "click en ".plus(position))
        }
        container.addView(itemView)

        return itemView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    interface ImageSlider {
        fun onSliderImageClick(itemView: View, position: Int, urlImage: String)
    }
}