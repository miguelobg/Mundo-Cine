package com.example.mundocine.database

import com.example.mundocine.R
import com.example.mundocine.modelos.Genero
import com.example.mundocine.modelos.Pelicula

class GeneroDAO {
    companion object {
        var listaGeneros = ArrayList<Genero>()
        var listaPeliculas = ArrayList<Pelicula>()

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

    init {
        listaPeliculas.add(Pelicula("El Padrino", R.drawable.jack_sparrow, "Acción", "2h 55min", 9.2, "El Padrino es una película estadounidense de 1972 dirigida por Francis Ford Coppola. El filme fue producido por Albert S. Ruddy, desde un guion de Mario Puzo y Coppola. Está basada en la novela homónima de Puzo.", "Francis Ford Coppola", 1972))
        listaPeliculas.add(Pelicula("El Padrino II", R.drawable.jack_sparrow, "Acción", "3h 20min", 9.0, "El Padrino II es una película estadounidense de 1974 dirigida por Francis Ford Coppola. Es la secuela de El Padrino (1972) y la segunda entrega de la trilogía de El Padrino. La película es una secuela y precuela simultánea de la primera parte, presentando dos historias paralelas.", "Francis Ford Coppola", 1974))
    }



}