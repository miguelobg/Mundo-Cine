package com.example.mundocine.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocine.R
import com.example.mundocine.database.GeneroDAO

class AdaptadorGenero(private val onGeneroClick: (String) -> Unit): RecyclerView.Adapter<AdaptadorGenero.ViewHolder>() {

    inner class ViewHolder(vistaGenero: View) : RecyclerView.ViewHolder(vistaGenero){
        var nombre : TextView = vistaGenero.findViewById(R.id.tvGenero)
        var portada : ImageView = vistaGenero.findViewById(R.id.ivGenero)

        init {
            vistaGenero.setOnClickListener {
                val posicion = adapterPosition
                if (posicion != RecyclerView.NO_POSITION) {
                    val generoSeleccionado = GeneroDAO.listaGeneros[posicion].nombre
                    onGeneroClick(generoSeleccionado)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_genero,parent,false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return GeneroDAO.listaGeneros.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genero = GeneroDAO.listaGeneros[position]
        holder.nombre.text = genero.nombre
        holder.portada.setImageResource(genero.portada)
    }


}