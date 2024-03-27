package com.github.fajaragungpramana.our.widget.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}