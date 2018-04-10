package br.com.livroandroid.carros.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Sanatiel on 07/04/2018.
 */
fun Fragment.toast(message:CharSequence, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(activity, message, length).show()

fun Fragment.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(activity,message,length).show()
