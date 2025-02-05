package com.example.mundocine.actividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mundocine.R
import com.example.mundocine.database.DaoPeliculas
import com.example.mundocine.modelos.Pelicula

class AgregarPelicula : AppCompatActivity() {

    private lateinit var daoPeliculas: DaoPeliculas
    private var portadaSeleccionada = "pelicula_poster_por_defecto"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_pelicula)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etDirector = findViewById<EditText>(R.id.etDirector)
        val etaño = findViewById<EditText>(R.id.etaño)
        val etCalificacion = findViewById<EditText>(R.id.etCalificacion)
        val etSinopsis = findViewById<EditText>(R.id.etSinopsis)
        val spinnerGenero = findViewById<Spinner>(R.id.spinnerGenero)

        val ivPortadaSeleccionada = findViewById<ImageView>(R.id.ivPortadaSeleccionada)
        val rgPortadas = findViewById<RadioGroup>(R.id.rgPortadas)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnCancelar = findViewById<Button>(R.id.btnCancelar)

        daoPeliculas = DaoPeliculas(this)

        // generos para el spinner de agregar pelicula
        val generos = listOf("Acción", "Aventura", "Drama", "Comedia", "Sci-Fi", "Romance", "Terror", "Animación")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)
        spinnerGenero.adapter = adapter

        val portadas = mapOf(
            R.id.rbPortada1 to "pelicula_poster_por_defecto",
            R.id.rbPortada2 to "pelicula_poster_por_defecto2",
            R.id.rbPortada3 to "pelicula_poster_por_defecto3",
            R.id.rbPortada4 to "pelicula_poster_por_defecto4"
        )

        val imagenesPortada = mapOf(
            R.id.rbPortada1 to R.drawable.pelicula_poster_por_defecto,
            R.id.rbPortada2 to R.drawable.pelicula_poster_por_defecto2,
            R.id.rbPortada3 to R.drawable.pelicula_poster_por_defecto3,
            R.id.rbPortada4 to R.drawable.pelicula_poster_por_defecto4
        )

       // cambio de portada con el radiobutton
        rgPortadas.setOnCheckedChangeListener { _, checkedId ->
            portadaSeleccionada = portadas[checkedId] ?: "pelicula_1"
            ivPortadaSeleccionada.setImageResource(imagenesPortada[checkedId] ?: R.drawable.pelicula_poster_sin_imagen)
        }


        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val director = etDirector.text.toString()
            val año = etaño.text.toString().toIntOrNull() ?: 0
            val calificacion = etCalificacion.text.toString().toDoubleOrNull() ?: 0.0
            val sinopsis = etSinopsis.text.toString()
            val genero = spinnerGenero.selectedItem.toString()

    //validaciones para agregar pelicula
            if (titulo.isEmpty()) {
                Toast.makeText(this, "El título no puede estar vacío", Toast.LENGTH_SHORT).show()
            } else if (director.isEmpty()) {
                Toast.makeText(this, "El director no puede estar vacío", Toast.LENGTH_SHORT).show()
            } else if (año <= 0) {
                Toast.makeText(this, "El año debe ser mayor que 0", Toast.LENGTH_SHORT).show()
            } else if (calificacion < 0 || calificacion > 10) {
                Toast.makeText(this, "La calificación debe estar entre 0 y 10", Toast.LENGTH_SHORT).show()
            } else {
                val nuevaPelicula = Pelicula(
                    titulo = titulo,
                    director = director,
                    genero = genero,
                    año = año,
                    calificacion = calificacion,
                    sinopsis = sinopsis,
                    portada = portadaSeleccionada
                )
                daoPeliculas.insertarPelicula(nuevaPelicula)

                // Enviar el resultado a MainActivity
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}
