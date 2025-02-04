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
    private var portadaSeleccionada = "pelicula_1" // Valor por defecto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_pelicula)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etDirector = findViewById<EditText>(R.id.etDirector)
        val etAnio = findViewById<EditText>(R.id.etAnio)
        val etCalificacion = findViewById<EditText>(R.id.etCalificacion)
        val etSinopsis = findViewById<EditText>(R.id.etSinopsis)
        val spinnerGenero = findViewById<Spinner>(R.id.spinnerGenero)

        val ivPortadaSeleccionada = findViewById<ImageView>(R.id.ivPortadaSeleccionada)
        val rgPortadas = findViewById<RadioGroup>(R.id.rgPortadas)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnCancelar = findViewById<Button>(R.id.btnCancelar)

        daoPeliculas = DaoPeliculas(this)

        // Lista de géneros predefinidos
        val generos = listOf("Acción", "Aventura", "Drama", "Comedia", "Sci-Fi", "Romance", "Terror", "Animación")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)
        spinnerGenero.adapter = adapter

        // Mapeo de RadioButton con nombres de imágenes
        val portadas = mapOf(
            R.id.rbPortada1 to "pelicula_1",
            R.id.rbPortada2 to "pelicula_2",
            R.id.rbPortada3 to "pelicula_3",
            R.id.rbPortada4 to "pelicula_4"
        )

        // Mapeo de RadioButton con IDs de imágenes en drawable
        val imagenesPortada = mapOf(
            R.id.rbPortada1 to R.drawable.pelicula_poster_por_defecto,
            R.id.rbPortada2 to R.drawable.pelicula_poster_por_defecto2,
            R.id.rbPortada3 to R.drawable.pelicula_poster_por_defecto3,
            R.id.rbPortada4 to R.drawable.pelicula_poster_por_defecto4
        )

        // Cambiar imagen cuando se seleccione un RadioButton
        rgPortadas.setOnCheckedChangeListener { _, checkedId ->
            portadaSeleccionada = portadas[checkedId] ?: "pelicula_1"
            ivPortadaSeleccionada.setImageResource(imagenesPortada[checkedId] ?: R.drawable.pelicula_poster_sin_imagen)
        }

        // Botón Guardar
        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val director = etDirector.text.toString()
            val anio = etAnio.text.toString().toIntOrNull() ?: 0
            val calificacion = etCalificacion.text.toString().toDoubleOrNull() ?: 0.0
            val sinopsis = etSinopsis.text.toString()
            val genero = spinnerGenero.selectedItem.toString()

            if (titulo.isNotEmpty() && director.isNotEmpty() && anio > 0 && calificacion > 0) {
                val nuevaPelicula = Pelicula(
                    titulo = titulo,
                    director = director,
                    genero = genero,
                    anio = anio,
                    calificacion = calificacion,
                    sinopsis = sinopsis,
                    portada = portadaSeleccionada
                )
                daoPeliculas.insertarPelicula(nuevaPelicula)

                // Enviar el resultado a MainActivity
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón Cancelar
        btnCancelar.setOnClickListener {
            finish() // Cerrar la actividad sin guardar
        }
    }
}
