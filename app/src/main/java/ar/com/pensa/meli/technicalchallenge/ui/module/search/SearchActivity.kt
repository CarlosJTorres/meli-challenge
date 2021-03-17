package ar.com.pensa.meli.technicalchallenge.ui.module.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.com.pensa.meli.technicalchallenge.R

/**
 * Pantalla de Búsqueda dl Productos.
 */
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //--aqui mando el fragment
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.activity_search_fragment_container,
                SearchFragment.newInstance()
            )
            .commit()
    }
}