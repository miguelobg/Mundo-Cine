package com.example.mundocine.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.mundocine.modelos.Pelicula

class DaoPeliculas(context: Context) {
    private val dbHelper = MundoCineDBHelper(context)

    fun insertarPelicula(pelicula: Pelicula): Long {
        val db = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("titulo", pelicula.titulo)
            put("director", pelicula.director)
            put("genero", pelicula.genero)
            put("año", pelicula.año)
            put("calificacion", pelicula.calificacion)
            put("sinopsis", pelicula.sinopsis)
            put("portada", pelicula.portada)
        }
        return db.insert("Peliculas", null, valores)
    }

    fun obtenerPeliculasPorGenero(genero: String): List<Pelicula> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor =
            db.rawQuery("SELECT * FROM Peliculas WHERE genero = ?", arrayOf(genero))
        val peliculas = mutableListOf<Pelicula>()

        if (cursor.moveToFirst()) {
            do {
                val pelicula = Pelicula(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    director = cursor.getString(cursor.getColumnIndexOrThrow("director")),
                    genero = cursor.getString(cursor.getColumnIndexOrThrow("genero")),
                    año = cursor.getInt(cursor.getColumnIndexOrThrow("año")),
                    calificacion = cursor.getDouble(cursor.getColumnIndexOrThrow("calificacion")),
                    sinopsis = cursor.getString(cursor.getColumnIndexOrThrow("sinopsis")),
                    portada = cursor.getString(cursor.getColumnIndexOrThrow("portada"))
                )
                peliculas.add(pelicula)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return peliculas


    }

    fun obtenerActoresPorPelicula(idPelicula: Int): List<String> {
        val db = dbHelper.readableDatabase
        val actores = mutableListOf<String>()

        val cursor: Cursor = db.rawQuery("""
        SELECT Actores.nombre 
        FROM Actores
        INNER JOIN PeliculasActores ON Actores.id = PeliculasActores.idActor
        WHERE PeliculasActores.idPelicula = ?
    """, arrayOf(idPelicula.toString()))

        if (cursor.moveToFirst()) {
            do {
                actores.add(cursor.getString(cursor.getColumnIndexOrThrow("nombre")))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return actores
    }

    fun borrarPelicula(idPelicula: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("Peliculas", "id = ?", arrayOf(idPelicula.toString()))
    }

    fun actualizarPelicula(pelicula: Pelicula): Int {
        val db = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("titulo", pelicula.titulo)
            put("director", pelicula.director)
            put("genero", pelicula.genero)
            put("año", pelicula.año)
            put("calificacion", pelicula.calificacion)
            put("sinopsis", pelicula.sinopsis)
            put("portada", pelicula.portada)
        }
        return db.update("Peliculas", valores, "id = ?", arrayOf(pelicula.id.toString()))
    }

    // para recuperar la pelicula editada.
    fun obtenerPeliculaPorId(idPelicula: Int): Pelicula? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Peliculas WHERE id = ?", arrayOf(idPelicula.toString()))

        var pelicula: Pelicula? = null

        if (cursor.moveToFirst()) {
            pelicula = Pelicula(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                director = cursor.getString(cursor.getColumnIndexOrThrow("director")),
                genero = cursor.getString(cursor.getColumnIndexOrThrow("genero")),
                año = cursor.getInt(cursor.getColumnIndexOrThrow("año")),
                calificacion = cursor.getDouble(cursor.getColumnIndexOrThrow("calificacion")),
                sinopsis = cursor.getString(cursor.getColumnIndexOrThrow("sinopsis")),
                portada = cursor.getString(cursor.getColumnIndexOrThrow("portada"))
            )
        }
        cursor.close()
        return pelicula
    }






}