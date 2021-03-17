package ar.com.pensa.meli.technicalchallenge.ui.module.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.pensa.meli.technicalchallenge.R
import ar.com.pensa.meli.technicalchallenge.ui.model.ProductUIModel
import ar.com.pensa.meli.technicalchallenge.ui.module.detail.DetailActivity
import ar.com.pensa.meli.technicalchallenge.utils.CustomRecyclerViewListener
import ar.com.pensa.meli.technicalchallenge.utils.MyInfiniteScrollListener


/**
 * Fragment de Búsqueda, con implementación MVP.
 * El fragment implementa
 * @see CustomRecyclerViewListener<T> para saber cuando llega al final de la lista
 * y así pedir más elementos al API.
 */
class SearchFragment : Fragment(),
    SearchFragmentView, CustomRecyclerViewListener<ProductUIModel>,
    MyInfiniteScrollListener.HumanListener {
    //--
    private var presenter: SearchFragmentPresenter? = null
    private var adapter: ProductsAdapter? = null
    private var pageNumber: Int = 0
    private var textoABuscar: String = ""

    //-
    private var productsList: MutableList<ProductUIModel> = mutableListOf()

    //--Views
    private lateinit var rvFragmentSearchRv: RecyclerView
    private lateinit var svSearch: androidx.appcompat.widget.SearchView
    private lateinit var pbStatus: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }

    //----

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //--
        if (adapter == null) {
            this.adapter = ProductsAdapter(this)
        }

        svSearch = view.findViewById(R.id.fragment_search_sv_search)
        rvFragmentSearchRv = view.findViewById(R.id.rv_fragment_search_rv)
        pbStatus = view.findViewById(R.id.fragment_search_pb_status)
        //-
        rvFragmentSearchRv.layoutManager = LinearLayoutManager(context)
        rvFragmentSearchRv.adapter = this.adapter

        //--
        if (presenter == null) {
            presenter = SearchFragmentPresenter(this)
        }

        ///---
        setViewListeners()

    }

    /**
     * Configura el listenre del SearchView
     */
    private fun setViewListeners() {

        //...
        rvFragmentSearchRv.addOnScrollListener(MyInfiniteScrollListener(this))

        //- Busqueda Listener
        svSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("torrancio", "onQueryTextSubmit:".plus(query.toString()))

                textoABuscar = query.toString();
                pageNumber = 0;
                getProductsByTextSearch(textoABuscar, pageNumber)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("torrancio", "onQueryTextChange:".plus(newText.toString()))
                return false
            }

        })
    }

    /**
     * Considera el número de página y hace request de búsqueda
     */
    private fun getProductsByTextSearch(texto: String, pageNumber: Int) {
        Log.d("torrancio", "getProductsByTextSearch($texto,$pageNumber)")

        if (pageNumber == 0) {
            productsList.removeAll(productsList)

            if (adapter != null) {
                adapter!!.removeItems()
            }
        }

        if (texto.isNotEmpty()) {
            presenter!!.getProducts(texto, pageNumber)
        }

    }

    /**
     * En este punto se obtuvieron los productos de la búsqueda.
     */
    override fun onGetProducts(productUIModelsList: MutableList<ProductUIModel>, results: Int) {
        Log.d(
            "torrancio",
            "onGetProducts:list?=".plus(productUIModelsList.size).plus(" ").plus(results)
        )
        pbStatus.visibility = View.GONE

        //---
        Toast
            .makeText(
                context,
                "Obtenidos +" + productUIModelsList!!.size.toString(),
                Toast.LENGTH_SHORT
            )
            .show()
        //--
        if (productUIModelsList.isNotEmpty()) {
            pageNumber++

            for (product in productUIModelsList) {

                productsList.add(product)
            }
        } else {
            Toast.makeText(context, "Últimas productos disponibles", Toast.LENGTH_SHORT).show()
        }

        //-
        if (adapter != null) {
            adapter!!.insertItems(productsList)
            adapter!!.notifyDataSetChanged()
        }
    }

    /**
     * A este punto el presenter avisa que está cargando datos.
     */
    override fun onLoading() {
        pbStatus.visibility = View.VISIBLE
    }

    /**
     * A este punto el presenter avisa que hubo un error en la carga de datos
     */
    override fun onError(msg: String) {
        Log.d("torrancio", "ERROR FRAG:".plus(msg))
        pbStatus.visibility = View.GONE
    }

    /**
     * El listener de la lista/recycler avisa que un elmento fue clickeado
     * --> va a la pantalla de dettalle con algunas propiedades precargadas.
     */
    override fun onItemClicked(item: ProductUIModel, itemView: View, position: Int) {
        startActivity(
            DetailActivity
                .getCallingIntent(
                    itemView.context,
                    item.id,
                    item.title ?: "",
                    item.price,
                    item.thumbnail,
                    item.condition,
                    item.permalink
                )
        )
    }


    /**
     * Se llegó al ultimo elemento cargado de la lista. (se solictan más al API)
     */
    override fun onReachLastLoadedItem(
        recyclerView: RecyclerView?,
        lastItemView: View?,
        lastItemPosition: Int
    ) {
        Log.d("torrancio", "onReachLastLoadedItem")
        Log.d("search", "onReachLastLoadedItem($lastItemPosition)")
        if (lastItemPosition != -1) {
            getProductsByTextSearch(textoABuscar, pageNumber)
        }
    }

    override fun onScrolling(recyclerView: RecyclerView?, dx: Int, dy: Int, canScrollUp: Boolean) {
        //--Log.d("torrancio","onScrolling")
    }

    override fun onGetTop(recyclerView: RecyclerView?) {
        //--Log.d("torrancio","onGetTop")
    }
}