package com.example.mundocine.actividades

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocine.R
import com.example.mundocine.adaptadores.AdaptadorGenero
import com.example.mundocine.database.GeneroDAO
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var rv : RecyclerView
    var dao = GeneroDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rvGeneros)
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rv.adapter = AdaptadorGenero {
            generoSleccionado ->
            val intent = Intent(this, MenuPelicula::class.java)
            intent.putExtra("GENERO_SELECCIONADO", generoSleccionado)
            startActivity(intent)
        }

        val fabAgregarPelicula = findViewById<FloatingActionButton>(R.id.fabAgregarPelicula)
        fabAgregarPelicula.setOnClickListener {
            val intent = Intent(this, AgregarPelicula::class.java)
            startActivityForResult(intent, 1)
        }


        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            // Acción del icono de navegación
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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



}