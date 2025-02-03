
package com.example.mundocine.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mundocine.R
import com.example.mundocine.database.DaoPeliculas


class DetallePeliculaFragment : Fragment() {

    private lateinit var imgPoster: ImageView
    private lateinit var txtTitulo: TextView
    private lateinit var txtGenero: TextView
    private lateinit var txtSinopsis: TextView
    private lateinit var txtDirector: TextView
    private lateinit var txtValoracion: TextView
    private lateinit var btnActores: Button

    private lateinit var daoPeliculas: DaoPeliculas


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

        daoPeliculas = DaoPeliculas(requireContext())

        arguments?.let {
            txtTitulo.text = it.getString("TITULO")
            txtGenero.text = it.getString("GENERO")
            txtSinopsis.text = it.getString("SINOPSIS")
            txtDirector.text = it.getString("DIRECTOR")
            txtValoracion.text = "Valoración: ${it.getDouble("VALORACION")}/10"
            imgPoster.setImageResource(it.getInt("IMAGEN"))

            val idPelicula = it.getInt("ID_PELICULA")

            btnActores.setOnClickListener {
                val actores = daoPeliculas.obtenerActoresPorPelicula(idPelicula)
                ActoresDialogFragment(actores).show(parentFragmentManager, "actoresDialog")
            }

        }
    }
}
//        btnActores.setOnClickListener {
//            val actoresFragment = ActoresFragment()
//            actoresFragment.show(parentFragmentManager, "actoresDialog")
//        }
//
//        btnBandaSonora.setOnClickListener {
//            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.banda_sonora)
//            mediaPlayer?.start()
//        }
//
//        btnFavoritos.setOnClickListener {
//            viewModel.agregarAFavoritos()
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer?.release()
//        mediaPlayer = null
//    }
//}