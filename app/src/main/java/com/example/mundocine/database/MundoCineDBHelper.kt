package com.example.mundocine.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MundoCineDBHelper(contexto: Context) : SQLiteOpenHelper(contexto, DATABASE_NOMBRE, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tablas
        db.execSQL(CREATE_TABLE_PELICULAS)
        db.execSQL(CREATE_TABLE_ACTORES)
        db.execSQL(CREATE_TABLE_PELICULAS_ACTORES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Si hay una nueva versión, eliminar y volver a crear las tablas
        db.execSQL("DROP TABLE IF EXISTS PeliculasActores")
        db.execSQL("DROP TABLE IF EXISTS Peliculas")
        db.execSQL("DROP TABLE IF EXISTS Actores")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NOMBRE = "MundoCine.db"
        const val DATABASE_VERSION = 1

        const val CREATE_TABLE_PELICULAS = """
            CREATE TABLE Peliculas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                director TEXT,
                genero TEXT,
                anio INTEGER,
                calificacion INTEGER
            );
        """

        const val CREATE_TABLE_ACTORES = """
            CREATE TABLE Actores (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                edad INTEGER
            );
        """

        const val CREATE_TABLE_PELICULAS_ACTORES = """
            CREATE TABLE PeliculasActores (
                idPelicula INTEGER,
                idActor INTEGER,
                FOREIGN KEY (idPelicula) REFERENCES Peliculas(id),
                FOREIGN KEY (idActor) REFERENCES Actores(id),
                PRIMARY KEY (idPelicula, idActor)
            );
        """
    }
}