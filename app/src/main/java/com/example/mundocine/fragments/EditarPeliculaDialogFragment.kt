package com.example.mundocine.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mundocine.R
import com.example.mundocine.database.DaoPeliculas
import com.example.mundocine.modelos.Pelicula

class EditarPeliculaDialogFragment(private val pelicula: Pelicula, private val onUpdate: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val daoPeliculas = DaoPeliculas(context)

        val builder = AlertDialog.Builder(context)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_editar_pelicula, null)

        val etTitulo = view.findViewById<EditText>(R.id.etTitulo)
        val etDirector = view.findViewById<EditText>(R.id.etDirector)
        val etAño = view.findViewById<EditText>(R.id.etaño)
        val etCalificacion = view.findViewById<EditText>(R.id.etCalificacion)
        val etSinopsis = view.findViewById<EditText>(R.id.etSinopsis)
        val spinnerGenero = view.findViewById<Spinner>(R.id.spinnerGenero)

        //cargamos los datos de la pelicula en los et
        etTitulo.setText(pelicula.titulo)
        etDirector.setText(pelicula.director)
        etAño.setText(pelicula.año.toString())
        etCalificacion.setText(pelicula.calificacion.toString())
        etSinopsis.setText(pelicula.sinopsis)

        val generos = listOf("Acción", "Aventura", "Drama", "Comedia", "Sci-Fi", "Romance", "Terror", "Animación")
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, generos)
        spinnerGenero.adapter = adapter
        spinnerGenero.setSelection(generos.indexOf(pelicula.genero))

        builder.setView(view)
            .setTitle("Editar Película")
            .setPositiveButton("Guardar") { _, _ ->}
            .setNegativeButton("Cancelar", null)

                val dialog = builder.create()

        dialog.setOnShowListener {
            val botonGuardar = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            botonGuardar.setOnClickListener {
                val nuevoTitulo = etTitulo.text.toString()
                val nuevoDirector = etDirector.text.toString()
                val nuevoAño = etAño.text.toString().toIntOrNull()
                val nuevaCalificacion = etCalificacion.text.toString().toDoubleOrNull()
                val nuevaSinopsis = etSinopsis.text.toString()
                val nuevoGenero = spinnerGenero.selectedItem.toString()

                if (nuevoAño == null || nuevoAño <= 0) {
                    Toast.makeText(context, "El año debe ser mayor que 0.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (nuevaCalificacion != null && nuevaCalificacion > 10) {
                    Toast.makeText(context, "La calificación no puede ser mayor que 10.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Se mantiene la imagen actual
                val peliculaEditada = pelicula.copy(
                    titulo = nuevoTitulo,
                    director = nuevoDirector,
                    año = nuevoAño,
                    calificacion = nuevaCalificacion ?: pelicula.calificacion,
                    sinopsis = nuevaSinopsis,
                    genero = nuevoGenero,
                    portada = pelicula.portada  // Mantenemos la imagen original
                )

                daoPeliculas.actualizarPelicula(peliculaEditada)
                onUpdate()
                dialog.dismiss()
            }
        }

        return dialog
    }
}