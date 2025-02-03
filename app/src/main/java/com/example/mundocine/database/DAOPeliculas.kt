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
            put("anio", pelicula.anio)
            put("calificacion", pelicula.calificacion)
        }
        return db.insert("Peliculas", null, valores)
    }

    fun obtenerPeliculas(): List<Pelicula> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Peliculas", null, null, null, null, null, "anio DESC")
        val peliculas = mutableListOf<Pelicula>()

        if (cursor.moveToFirst()) {
            do {
                val pelicula = Pelicula(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    director = cursor.getString(cursor.getColumnIndexOrThrow("director")),
                    genero = cursor.getString(cursor.getColumnIndexOrThrow("genero")),
                    anio = cursor.getInt(cursor.getColumnIndexOrThrow("anio")),
                    calificacion = cursor.getDouble(cursor.getColumnIndexOrThrow("calificacion"))
                )
                peliculas.add(pelicula)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return peliculas
    }
}