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
import com.example.mundocine.fragments.DetallePeliculaFragment
import com.google.android.material.appbar.MaterialToolbar

class MenuPelicula : AppCompatActivity() {

    lateinit var rvPelicula : RecyclerView
    lateinit var daoPeliculas: DaoPeliculas
    lateinit var adaptadorPelicula: AdaptadorPelicula



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_pelicula)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            // Acción del icono de navegación
        }

        daoPeliculas = DaoPeliculas(this)

        rvPelicula = findViewById(R.id.rvPeliculas)

        val generoSeleccionado = intent.getStringExtra("GENERO_SELECCIONADO") ?: ""
        cargarPeliculasPorGenero(generoSeleccionado)

        // Actualizar el rvPeliculas cuando se borra una película
        supportFragmentManager.setFragmentResultListener("eliminar_pelicula", this) { _, bundle ->
            val actualizar = bundle.getBoolean("actualizar", false)
            if (actualizar) {
                cargarPeliculasPorGenero(generoSeleccionado)
            }
        }

        //filtramos por genero
        //val peliculasFiltradas = GeneroDAO.listaPeliculas.filter { it.genero == generoSeleccionado }
//        rvPelicula = findViewById(R.id.rvPeliculas)
//        rvPelicula.adapter = AdaptadorPelicula(peliculasFiltradas, this) //No value passed for parameter 'activity'
//        rvPelicula.layoutManager = LinearLayoutManager(this)



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
                true
            }
            R.id.action_voice_search -> {
                Toast.makeText(this, "Búsqueda por voz...", Toast.LENGTH_SHORT).show()
                // Aquí puedes lanzar el reconocimiento de voz con SpeechRecognizer
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

    override fun onResume() {
        super.onResume()
        val idPelicula = intent.getIntExtra("ID_PELICULA", -1)
        if (idPelicula != -1) {
            val fragment = DetallePeliculaFragment().apply {
                arguments = Bundle().apply {
                    putInt("ID_PELICULA", idPelicula)
                    putString("TITULO", intent.getStringExtra("TITULO"))
                    putString("GENERO", intent.getStringExtra("GENERO"))
                    putString("SINOPSIS", intent.getStringExtra("SINOPSIS"))
                    putString("DIRECTOR", intent.getStringExtra("DIRECTOR"))
                    putDouble("VALORACION", intent.getDoubleExtra("VALORACION", 0.0))
                    putInt("IMAGEN", intent.getIntExtra("IMAGEN", R.drawable.pelicula_poster_por_defecto))
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        } else {
            val generoSeleccionado = intent.getStringExtra("GENERO_SELECCIONADO") ?: ""
            cargarPeliculasPorGenero(generoSeleccionado)
        }
    }





}