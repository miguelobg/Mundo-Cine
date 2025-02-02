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
        listaPeliculas.add(Pelicula("El Padrino", R.drawable.jack_sparrow, "Crimen", "2h 55m", 9.2, "El Padrino es una película estadounidense de 1972 dirigida por Francis Ford Coppola. El filme fue producido por Albert S. Ruddy, con guion de Mario Puzo y Coppola, basado en la novela homónima de Puzo."))
        listaPeliculas.add(Pelicula("El Señor de los Anillos: El Retorno del Rey", R.drawable.jack_sparrow, "Fantasía", "3h 21m", 8.9, "El Señor de los Anillos: El Retorno del Rey es una película de fantasía dirigida por Peter Jackson y basada en el tercer volumen de la novela El Señor de los Anillos, de J. R. R. Tolkien."))
        listaPeliculas.add(Pelicula("El Rey León", R.drawable.jack_sparrow, "Animación", "1h 58m", 8.5, "El Rey León es una película animada producida por Walt Disney Feature Animation y distribuida por Walt Disney Pictures."))
        listaPeliculas.add(Pelicula("Titanic", R.drawable.jack_sparrow, "Romance", "3h 15m", 7.8, "Titanic es una película estadounidense dramática y romántica de 1997 dirigida, escrita, coeditada y co-producida por James Cameron."))
        listaPeliculas.add(Pelicula("El Exorcista", R.drawable.jack_sparrow, "Horror", "2h 2m", 8.0, "El exorcista es una película de terror estadounidense de 1973 dirigida por William Friedkin y producida por William Peter Blatty."))
        listaPeliculas.add(Pelicula("El Gran Showman", R.drawable.jack_sparrow, "Musical", "1h 45m", 7.6, "El Gran Showman es una película musical estadounidense de 2017 dirigida por Michael Gracey en su debut como director y escrita por Jenny Bicks y Bill Condon."))
        listaPeliculas.add(Pelicula("El Silencio de los Inocentes", R.drawable.jack_sparrow, "Thriller", "1h 58m", 8.6, "El silencio de los inocentes es una película de suspenso psicológico estadounidense de 1991 dirigida por Jonathan Demme y escrita por Ted Tally."))
        listaPeliculas.add(Pelicula("El Origen", R.drawable.jack_sparrow, "Sci-Fi", "2h 28m", 8.8, "Origen es una película de ciencia ficción escrita, dirigida y producida por Christopher Nolan."))
        listaPeliculas.add(Pelicula("El Resplandor", R.drawable.jack_sparrow, "Accion", "2h 26m", 8.4, "El resplandor es una película de terror psicológico sobrenatural estadounidense de 1980 producida y dirigida por Stanley Kubrick."))
    }



}