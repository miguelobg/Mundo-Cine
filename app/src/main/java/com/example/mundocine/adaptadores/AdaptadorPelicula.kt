package com.example.mundocine.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocine.R
import com.example.mundocine.modelos.Pelicula

class AdaptadorPelicula(private val listaPeliculas: List<Pelicula>) :
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
        holder.portada.setImageResource(pelicula.portada)
    }
}