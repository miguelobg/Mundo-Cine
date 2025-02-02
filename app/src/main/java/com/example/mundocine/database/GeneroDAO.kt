package com.example.mundocine.database

import com.example.mundocine.R
import com.example.mundocine.modelos.Genero

class GeneroDAO {
    companion object {
        var listaGeneros = ArrayList<Genero>()
    }

    init {
        listaGeneros.add(Genero("Acción", R.drawable.genero_accion))
        listaGeneros.add(Genero("Aventura", R.drawable.genero_aventura))
        listaGeneros.add(Genero("Sci-Fi", R.drawable.genero_sci_fi))
        listaGeneros.add(Genero("Comedia", R.drawable.genero_comedia))
        listaGeneros.add(Genero("Drama", R.drawable.genero_drama))
        listaGeneros.add(Genero("Fantasía", R.drawable.genero_fantasia))
        listaGeneros.add(Genero("Musical", R.drawable.genero_musical))
        listaGeneros.add(Genero("Romance", R.drawable.genero_romance))
        listaGeneros.add(Genero("Animación", R.drawable.genero_animacion))
        listaGeneros.add(Genero("Crimen", R.drawable.genero_crimen))
        listaGeneros.add(Genero("Misterio", R.drawable.genero_misterio))
        listaGeneros.add(Genero("Familia", R.drawable.genero_familia))
        listaGeneros.add(Genero("Guerra", R.drawable.genero_guerra))
        listaGeneros.add(Genero("Thriller", R.drawable.genero_thriller))
        listaGeneros.add(Genero("Música", R.drawable.genero_musica))
        listaGeneros.add(Genero("Horror", R.drawable.genero_horror))

    }
}