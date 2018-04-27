package br.com.livroandroid.carros.extensions

import android.widget.TextView

/**
 * Created by Sanatiel on 21/04/2018.
 */
//extensao para criar um get/set para um textView, bem como uma verificacao se ela esta vazia
var TextView.string: String
    get() = text.toString()
    set(value) { text = value }
fun TextView.isEmpty() = text.trim().isEmpty()

