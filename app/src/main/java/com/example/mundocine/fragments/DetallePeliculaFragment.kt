
package com.example.mundocine.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.mundocine.R
import com.example.mundocine.actividades.BandaSonora
import com.example.mundocine.database.DaoPeliculas
import com.example.mundocine.modelos.Pelicula


class DetallePeliculaFragment : Fragment() {

    private lateinit var imgPoster: ImageView
    private lateinit var txtTitulo: TextView
    private lateinit var txtGenero: TextView
    private lateinit var txtSinopsis: TextView
    private lateinit var txtDirector: TextView
    private lateinit var txtValoracion: TextView
    private lateinit var btnActores: Button
    private lateinit var btnBorrar: Button
    private lateinit var daoPeliculas: DaoPeliculas
    private lateinit var btnEditar: Button
    private lateinit var btnBandaSonora: Button
    private var pelicula: Pelicula? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_pelicula, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgPoster = view.findViewById(R.id.imgPoster)
        txtTitulo = view.findViewById(R.id.txtTitulo)
        txtGenero = view.findViewById(R.id.txtGenero)
        txtSinopsis = view.findViewById(R.id.txtSinopsis)
        txtDirector = view.findViewById(R.id.txtDirector)
        txtValoracion = view.findViewById(R.id.txtValoracion)
        btnActores = view.findViewById(R.id.btnActores)
        btnBorrar = view.findViewById(R.id.btnBorrar)
        btnEditar = view.findViewById(R.id.btnEditar)
        btnBandaSonora = view.findViewById(R.id.btnBandaSonora)

        daoPeliculas = DaoPeliculas(requireContext())


        // Obtener datos cuando se selecciona una película
        arguments?.let {
            val idPelicula = it.getInt("ID_PELICULA")
            val titulo = it.getString("TITULO") ?: ""
            val genero = it.getString("GENERO") ?: ""
            val sinopsis = it.getString("SINOPSIS") ?: ""
            val director = it.getString("DIRECTOR") ?: ""
            val calificacion = it.getDouble("VALORACION", 0.0)
            val año = it.getInt("AÑO", 2000)
            val portada = it.getInt("IMAGEN")

            pelicula = Pelicula(idPelicula, titulo, director, genero, año, calificacion, sinopsis, "")

            txtTitulo.text = titulo
            txtGenero.text = genero
            txtSinopsis.text = sinopsis
            txtDirector.text = director
            txtValoracion.text = "Valoración: $calificacion/10"
            imgPoster.setImageResource(portada)

            btnActores.setOnClickListener {
                val actores = daoPeliculas.obtenerActoresPorPelicula(idPelicula)
                ActoresDialogFragment(actores).show(parentFragmentManager, "actoresDialog")
            }

            btnBorrar.setOnClickListener {
                dialogoConfirmacion(idPelicula)
            }

            btnEditar.setOnClickListener {
                pelicula?.let { peli ->
                    EditarPeliculaDialogFragment(peli) {
                        actualizarVista(peli.id)
                    }.show(parentFragmentManager, "EditarPelicula")
                }
            }

            btnBandaSonora.setOnClickListener {
                val intent = Intent(requireContext(), BandaSonora::class.java)
                startActivity(intent)
            }


        }
    }

    private fun dialogoConfirmacion(idPelicula: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Confirmar Borrado")
            .setMessage("¿Estás seguro de que deseas borrar esta película?")
            .setPositiveButton("Sí") { dialog, _ ->
                daoPeliculas.borrarPelicula(idPelicula)
                Toast.makeText(requireContext(), "Película borrada", Toast.LENGTH_SHORT).show()

                // enviar resultado de borrado
                setFragmentResult("eliminar_pelicula", Bundle().apply {
                    putBoolean("actualizar", true)
                })

                dialog.dismiss()
                requireActivity().onBackPressed() // Volver a la lista de películas
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun actualizarVista(idPelicula: Int) {
        daoPeliculas.obtenerPeliculaPorId(idPelicula)?.let { peliculaActualizada: Pelicula ->
            txtTitulo.text = peliculaActualizada.titulo
            txtGenero.text = peliculaActualizada.genero
            txtSinopsis.text = peliculaActualizada.sinopsis
            txtDirector.text = peliculaActualizada.director
            txtValoracion.text = "Valoración: ${peliculaActualizada.calificacion}/10"

            val idImagen = requireContext().resources.getIdentifier(
                peliculaActualizada.portada, "drawable", requireContext().packageName
            )
            if (idImagen != 0) {
                imgPoster.setImageResource(idImagen)
            } else {
                imgPoster.setImageResource(R.drawable.pelicula_poster_por_defecto)
            }

            setFragmentResult("actualizar_lista", Bundle().apply {
                putBoolean("actualizar", true)
            })
        }
    }
}