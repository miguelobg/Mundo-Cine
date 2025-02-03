package com.example.mundocine.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ActoresDialogFragment(private val actores: List<String>) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Actores de la película")
        builder.setItems(actores.toTypedArray()) { _, _ -> }
        builder.setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }

        return builder.create()
    }
}
