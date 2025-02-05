package com.example.mundocine.actividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mundocine.R
import com.example.mundocine.database.DaoPeliculas
import com.example.mundocine.modelos.Pelicula

class EditarPelicula : AppCompatActivity() {

    private lateinit var daoPeliculas: DaoPeliculas
    private var idPelicula: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pelicula)

        daoPeliculas = DaoPeliculas(this)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etDirector = findViewById<EditText>(R.id.etDirector)
        val etAnio = findViewById<EditText>(R.id.etAnio)
        val etCalificacion = findViewById<EditText>(R.id.etCalificacion)
        val etSinopsis = findViewById<EditText>(R.id.etSinopsis)
        val spinnerGenero = findViewById<Spinner>(R.id.spinnerGenero)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnCancelar = findViewById<Button>(R.id.btnCancelar)

        // Obtener datos de la película
        idPelicula = intent.getIntExtra("ID_PELICULA", -1)
        etTitulo.setText(intent.getStringExtra("TITULO"))
        etDirector.setText(intent.getStringExtra("DIRECTOR"))
        etAnio.setText(intent.getIntExtra("ANIO", 0).toString())
        etCalificacion.setText(intent.getDoubleExtra("VALORACION", 0.0).toString())
        etSinopsis.setText(intent.getStringExtra("SINOPSIS"))

        // Configurar Spinner de géneros
        val generos = listOf("Acción", "Aventura", "Drama", "Comedia", "Sci-Fi", "Romance", "Terror", "Animación")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)
        spinnerGenero.adapter = adapter
        spinnerGenero.setSelection(generos.indexOf(intent.getStringExtra("GENERO")))

        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val director = etDirector.text.toString()
            val anio = etAnio.text.toString().toIntOrNull() ?: 0
            val calificacion = etCalificacion.text.toString().toDoubleOrNull() ?: 0.0
            val sinopsis = etSinopsis.text.toString()
            val genero = spinnerGenero.selectedItem.toString()

            if (titulo.isEmpty() || director.isEmpty() || anio <= 0 || calificacion < 0 || calificacion > 10) {
                Toast.makeText(this, "Por favor, complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peliculaActualizada = Pelicula(idPelicula, titulo, director, genero, anio, calificacion, sinopsis, "pelicula_poster_por_defecto")

            daoPeliculas.actualizarPelicula(peliculaActualizada)

            Toast.makeText(this, "Película actualizada", Toast.LENGTH_SHORT).show()

            // Crear intent para volver a la pantalla principal y limpiar la pila de actividades
            val intent = Intent(this, MenuPelicula::class.java).apply {
                putExtra("ID_PELICULA", idPelicula)
                putExtra("TITULO", titulo)
                putExtra("GENERO", genero)
                putExtra("SINOPSIS", sinopsis)
                putExtra("DIRECTOR", director)
                putExtra("VALORACION", calificacion)
                putExtra("IMAGEN", R.drawable.pelicula_poster_por_defecto) // Ajusta según sea necesario
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            finish() // Finalizamos la actividad actual
        }

        btnCancelar.setOnClickListener { finish() }
    }
}
