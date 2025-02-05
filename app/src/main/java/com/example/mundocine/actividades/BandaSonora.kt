package com.example.mundocine.actividades

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mundocine.R
import com.example.mundocine.modelos.Cancion

class BandaSonora : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    lateinit var albumCover: ImageView
    lateinit var  songName: TextView
    lateinit var  btnPrev: ImageButton
    lateinit var  btnPause: ImageButton
    lateinit var  btnNext: ImageButton
    lateinit var seekBar: SeekBar
    lateinit var  btnLoop: ImageButton
    lateinit var  tiempoLlevamos: TextView
    lateinit var  tiempoTotal: TextView

    private var cancionActual = 0
    private lateinit var canciones: List<Cancion>
    private val handler = Handler(Looper.getMainLooper())

    fun formatearTiempo(milisegundos: Int): String {
        val seg = (milisegundos / 1000) % 60
        val min = (milisegundos / (1000 * 60)) % 60
        return String.format("%02d:%02d", min, seg)
    }

    // Función para actualizar la UI con los datos de la canción actual
    fun actualizarCancion() {
        // TODO 4: Obtener los nuevos valores y ajustar la GUI
        val cancion = canciones[cancionActual]

        // Cambiar la canción en MediaPlayer
        mediaPlayer.reset()
        mediaPlayer = MediaPlayer.create(this, cancion.audio)
        mediaPlayer.setOnCompletionListener {
            if (cancionActual < canciones.size - 1) {
                cancionActual++
                actualizarCancion()
            } else {
                Toast.makeText(this, "Fin de la lista de reproducción", Toast.LENGTH_SHORT).show()
            }

        }
        songName.text = cancion.nombre
        albumCover.setImageResource(cancion.portada)
        tiempoTotal.text = formatearTiempo(mediaPlayer.duration)
        seekBar.max = mediaPlayer.duration

        mediaPlayer.start()
    }

    fun inicializarVistas(){
        albumCover =  findViewById(R.id.albumCover)
        songName = findViewById(R.id.textView)
        //val albumName: TextView = findViewById(R.id.albumName)
        btnPrev= findViewById(R.id.btnPrev)
        btnPause= findViewById(R.id.btnPause)
        btnNext = findViewById(R.id.btnNext)
        btnLoop = findViewById(R.id.btnLoop)
        tiempoTotal = findViewById(R.id.tiempoTotal)
        tiempoLlevamos = findViewById(R.id.tiempoLlevamos)
        seekBar = findViewById(R.id.seekBar)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banda_sonora)

        inicializarVistas()

        // Lista de canciones
        canciones = listOf(
            Cancion(R.drawable.pelicula_el_padrino,R.raw.godfather , "Godfather","El Padrino"),

        )

        // Inicializar MediaPlayer con la primera canción
        mediaPlayer = MediaPlayer.create(this, canciones[cancionActual].audio)
        actualizarCancion()

        // Botón Anterior
        btnPrev.setOnClickListener {
            if (cancionActual > 0) {
                cancionActual--
                actualizarCancion()
            }else{
                Toast.makeText(this, "No hay canciones anteriores", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón Pausa/Reanudar
        btnPause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                btnPause.setImageResource(android.R.drawable.ic_media_play)
            } else {
                mediaPlayer.start()
                btnPause.setImageResource(android.R.drawable.ic_media_pause)
            }
        }

        // Botón Siguiente
        btnNext.setOnClickListener {
            if (cancionActual < canciones.size - 1) {
                cancionActual++
                actualizarCancion()
            } else {
                Toast.makeText(this, "Fin de la lista de reproducción", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón Bucle
        btnLoop.setOnClickListener {
            // Esta os la doy hecha ;)
            mediaPlayer.isLooping = !mediaPlayer.isLooping
            Toast.makeText(
                this,
                if (mediaPlayer.isLooping) "Bucle activado" else "Bucle desactivado",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Listener del SeekBar para cambiar el progreso de la canción
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        gestionarBarra()
    }

    // Métofo onDestroy, liberamos recursos.
    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }


    private fun gestionarBarra() {
        handler.post(object : Runnable {
            override fun run() {
                if (::mediaPlayer.isInitialized) {
                    seekBar.progress = mediaPlayer.currentPosition
                    tiempoLlevamos.text = formatearTiempo(mediaPlayer.currentPosition)
                }
                handler.postDelayed(this, 1000) // Actualiza cada segundo
            }
        })
    }
}