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

        insertarDatos(db)

    }

    private fun insertarDatos(db: SQLiteDatabase) {

        // peliculas
        db.execSQL("""
            INSERT INTO Peliculas (titulo, director, genero, anio, calificacion, sinopsis, portada)
            VALUES ('El Padrino', 'Francis Ford Coppola', 'Drama', 1972, 9.2,
            'La familia Corleone es una de las cinco familias que dominan la mafia en Nueva York.',
            'pelicula_el_padrino');
        """)
        db.execSQL("""
            INSERT INTO Peliculas (titulo, director, genero, anio, calificacion, sinopsis, portada)
            VALUES ('El Padrino II', 'Francis Ford Coppola', 'Drama', 1974, 9.0,
            'La familia Corleone es una de las cinco familias que dominan la mafia en Nueva York.',
            'pelicula_el_padrino_2');
        """)
        db.execSQL(""" INSERT INTO Peliculas (titulo, director, genero, anio, calificacion, sinopsis, portada)
            VALUES ('El Padrino III', 'Francis Ford Coppola', 'Drama', 1990, 7.6,
            'La familia Corleone es una de las cinco familias que dominan la mafia en Nueva York.',
            'pelicula_el_padrino_3');
        """)
        db.execSQL("""
            INSERT INTO Peliculas (titulo, director, genero, anio, calificacion, sinopsis, portada)
            VALUES ('Scarface', 'Brian De Palma', 'Crimen', 1983, 8.3,
            'Un emigrante cubano se instala en Miami con el objetivo de convertirse en un gángster.',
            'pelicula_scarface');
        """)

        // actores
        db.execSQL("""
            INSERT INTO Actores (nombre, edad)
            VALUES ('Marlon Brando', 80);
        """)
        db.execSQL("""
            INSERT INTO Actores (nombre, edad)
            VALUES ('Al Pacino', 80);
        """)
        db.execSQL("""
            INSERT INTO Actores (nombre, edad)
            VALUES ('Robert De Niro', 77);
        """)

        // peliculas_actores
        db.execSQL("""
            INSERT INTO PeliculasActores (idPelicula, idActor)
            VALUES (1, 1);
        """)
        db.execSQL("""
            INSERT INTO PeliculasActores (idPelicula, idActor)
            VALUES (1, 2);
        """)
        db.execSQL("""
            INSERT INTO PeliculasActores (idPelicula, idActor)
            VALUES (2, 1);
            
            
        """)
        db.execSQL("""
            INSERT INTO PeliculasActores (idPelicula, idActor)
            VALUES (2, 2);
        """)
        db.execSQL("""
            INSERT INTO PeliculasActores (idPelicula, idActor)
            VALUES (2, 3);
        """)
        db.execSQL("""
            INSERT INTO PeliculasActores (idPelicula, idActor)
            VALUES (4, 2);
        """)

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
                calificacion INTEGER,
                sinopsis TEXT,
                portada TEXT 
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