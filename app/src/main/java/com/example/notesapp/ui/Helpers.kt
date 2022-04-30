package com.example.notesapp.ui

import android.content.Context
import android.widget.Toast
// With Kotlin extension functions it’s possible to display toasts as simple as this
fun Context.toast(message:String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
