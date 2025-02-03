package com.example.mundocine.modelos

data  class Pelicula (var titulo : String, var portada : Int, var genero : String, var duracion : String, var valoracion : Double, var sinopsis : String, var director : String, var anio : Int) {
    var actores: List<String> = emptyList()
    var bandaSonora: List<String> = emptyList()
}
