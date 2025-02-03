package com.example.mundocine.adaptadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocine.R
import com.example.mundocine.actividades.MenuPelicula
import com.example.mundocine.fragments.DetallePeliculaFragment
import com.example.mundocine.modelos.Pelicula

class AdaptadorPelicula(private val listaPeliculas: List<Pelicula>,
                        private val activity: FragmentActivity) :
    RecyclerView.Adapter<AdaptadorPelicula.ViewHolder>() {

    inner class ViewHolder(vistaPelicula: View) : RecyclerView.ViewHolder(vistaPelicula) {
        var titulo: TextView = vistaPelicula.findViewById(R.id.tvPelicula)
        var portada: ImageView = vistaPelicula.findViewById(R.id.ivPelicula)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int = listaPeliculas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = listaPeliculas[position]
        holder.titulo.text = pelicula.titulo
        //holder.portada.setImageResource(pelicula.portada)

        // Convertir el nombre de la imagen (ej. "pelicula_john_wick") en un ID de recurso drawable


        val idImagen = holder.itemView.context.resources.getIdentifier(
            pelicula.portada, "drawable", holder.itemView.context.packageName
        )

        if (idImagen != 0) { // Si encontró la imagen
            holder.portada.setImageResource(idImagen)
        } else { // Si no encontró la imagen, usar una imagen por defecto
            holder.portada.setImageResource(R.drawable.pelicula_poster_por_defecto)
        }

        holder.itemView.setOnClickListener {
            val fragment = DetallePeliculaFragment().apply {
                arguments = Bundle().apply {
                    putString("TITULO", pelicula.titulo)
                    putString("GENERO", pelicula.genero)
                    putString("SINOPSIS", pelicula.sinopsis)
                    putString("DIRECTOR", pelicula.director)
                    putDouble("VALORACION", pelicula.calificacion)
                    putInt("IMAGEN", idImagen)
                }
            }

            val activity = holder.itemView.context as MenuPelicula
            activity.findViewById<RecyclerView>(R.id.rvPeliculas).visibility = View.GONE
            activity.findViewById<FragmentContainerView>(R.id.fragment_container).visibility = View.VISIBLE

            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()

        }

    }


}