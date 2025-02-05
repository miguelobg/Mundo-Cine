package com.example.mundocine.modelos

data class Pelicula(
    val id: Int = 0,
    val titulo: String,
    val director: String,
    val genero: String,
    val año: Int,
    val calificacion: Double,
    val sinopsis: String,
    val portada: String
)