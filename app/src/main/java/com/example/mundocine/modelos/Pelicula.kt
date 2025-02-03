package com.example.mundocine.modelos

data class Pelicula(
    val id: Int = 0,
    val titulo: String,
    val director: String,
    val genero: String,
    val anio: Int,
    val calificacion: Double
)