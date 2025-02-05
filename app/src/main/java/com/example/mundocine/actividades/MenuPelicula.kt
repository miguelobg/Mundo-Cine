package com.example.mundocine.actividades

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocine.R
import com.example.mundocine.adaptadores.AdaptadorPelicula
import com.example.mundocine.database.DaoPeliculas
import com.example.mundocine.database.GeneroDAO
import com.google.android.material.appbar.MaterialToolbar

class MenuPelicula : AppCompatActivity() {

    lateinit var rvPelicula : RecyclerView
    lateinit var daoPeliculas: DaoPeliculas
    lateinit var adaptadorPelicula: AdaptadorPelicula
    private var generoSeleccionado: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_pelicula)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            // si da tiempo, icono de menu
        }

        daoPeliculas = DaoPeliculas(this)

        rvPelicula = findViewById(R.id.rvPeliculas)

        generoSeleccionado = intent.getStringExtra("GENERO_SELECCIONADO") ?: ""
        cargarPeliculasPorGenero(generoSeleccionado)

        // Escuchar eventos de actualización tras edición o eliminación
        supportFragmentManager.setFragmentResultListener("actualizar_lista", this) { _, bundle ->
            val actualizar = bundle.getBoolean("actualizar", false)
            if (actualizar) {
                cargarPeliculasPorGenero(generoSeleccionado)
            }
        }

        supportFragmentManager.setFragmentResultListener("eliminar_pelicula", this) { _, bundle ->
            val actualizar = bundle.getBoolean("actualizar", false)
            if (actualizar) {
                cargarPeliculasPorGenero(generoSeleccionado)
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun cargarPeliculasPorGenero(genero: String) {
        val listaPeliculas = daoPeliculas.obtenerPeliculasPorGenero(genero)

        adaptadorPelicula = AdaptadorPelicula(listaPeliculas, this)
        rvPelicula.layoutManager = LinearLayoutManager(this)
        rvPelicula.adapter = adaptadorPelicula
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Buscar...", Toast.LENGTH_SHORT).show()
                // filtrar por nombre - genero?
                true
            }
            R.id.action_voice_search -> {
                Toast.makeText(this, "Búsqueda por voz...", Toast.LENGTH_SHORT).show()
                // Mandar a crear pelicula
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            findViewById<RecyclerView>(R.id.rvPeliculas).visibility = View.VISIBLE
            findViewById<FragmentContainerView>(R.id.fragment_container).visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }





}