package ar.com.pensa.meli.technicalchallenge.utils

import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Listener para saber cuando se llega al final de una lista/RecyclerView
 * Además saber cuando llega al principio o cuando está scrolleando
 */
class MyInfiniteScrollListener : RecyclerView.OnScrollListener {

    private val NOT_SCROLLING = "NOT_SCROLLING"
    private val SCROLLING_UP = "SCROLLING_UP"
    private val SCROLLING_DOWN = "SCROLLING_DOWN"

    private var currentState = NOT_SCROLLING


    private var humanListener: HumanListener? = null

    constructor(@Nullable humanListener: HumanListener?) {
        this.humanListener = humanListener
    }

    private fun setCurrentState(newState: Int) {
        when (newState) {
            0 -> currentState = NOT_SCROLLING
            1 -> currentState = SCROLLING_UP
            2 -> currentState = SCROLLING_DOWN
        }
    }

    fun getCurrentState(): String {
        return currentState
    }


    fun getHumanListener(): HumanListener? {
        return humanListener
    }

    fun setHumanListener(humanListener: HumanListener?) {
        this.humanListener = humanListener
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        //Log.d("torrancio","onScrolled(dx:"+dx+",dy:"+dy+")");
        //Log.d("torrancio","onScrolled(dx:"+dx+",dy:"+dy+")");
        Log.d("torrancio", "canScrollUp(" + canScrollUp(recyclerView) + ")")
        if (humanListener != null) {

            //Cuando llega al tope superior
            if (isFirstElementVisible(recyclerView)) {
                humanListener!!.onGetTop(recyclerView)
            }

            //Cuando scrollead
            humanListener!!.onScrolling(recyclerView, dx, dy, isFirstElementVisible(recyclerView))

            //Cuando llega al último elemento
            if (isLastElementVisible(recyclerView)) {
                humanListener!!
                    .onReachLastLoadedItem(
                        recyclerView,
                        getLastElementVisible(recyclerView),
                        getLastElementPosition(recyclerView)
                    )
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        setCurrentState(newState)

        Log.d("torrancio", "onScrollStateChanged(newState:" + getCurrentState() + ")")
    }


    private fun canScrollUp(recyclerView: RecyclerView): Boolean {
        return recyclerView.canScrollVertically(-1)
    }

    private fun isLastElementVisible(recyclerView: RecyclerView): Boolean {
        val lastItemIndex =
            recyclerView.adapter!!.itemCount - 1
        val lastCompletelyVisibleItemPosition: Int =
            (Objects.requireNonNull(recyclerView.layoutManager) as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
        return lastItemIndex == lastCompletelyVisibleItemPosition
    }

    private fun isFirstElementVisible(recyclerView: RecyclerView): Boolean {
        val firstItemIndex =
            0 /*Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()-1;*/
        val lastCompletelyVisibleItemPosition: Int =
            (Objects.requireNonNull(recyclerView.layoutManager) as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        return firstItemIndex == lastCompletelyVisibleItemPosition
    }

    //--
    private fun getLastElementPosition(recyclerView: RecyclerView): Int {
        val layoutManager: LinearLayoutManager? =
            recyclerView.layoutManager as LinearLayoutManager?
        return layoutManager?.findLastCompletelyVisibleItemPosition()!!
    }

    private fun getLastElementVisible(recyclerView: RecyclerView): View? {
        val layoutManager: LinearLayoutManager? =
            recyclerView.layoutManager as LinearLayoutManager?
        val lastCompletelyVisibleItemPosition: Int =
            layoutManager!!.findLastCompletelyVisibleItemPosition()

        return layoutManager!!.findViewByPosition(lastCompletelyVisibleItemPosition)
    }


    //--
    interface HumanListener {
        fun onReachLastLoadedItem(
            @NonNull recyclerView: RecyclerView?,
            lastItemView: View?,
            lastItemPosition: Int
        )

        fun onScrolling(
            @NonNull recyclerView: RecyclerView?,
            dx: Int,
            dy: Int,
            canScrollUp: Boolean
        )

        fun onGetTop(@NonNull recyclerView: RecyclerView?)
    }
}