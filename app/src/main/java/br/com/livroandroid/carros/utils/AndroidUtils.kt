package br.com.livroandroid.carros.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Sanatiel on 16/04/2018.
 */
//classe Singleton(estatica) para metodos utilitarios
//met para verificar se existe conexao com a net
object AndroidUtils{

    fun isNetworkAvaliable(context: Context): Boolean{
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivity.allNetworks
        return networks
                .map { connectivity.getNetworkInfo(it) }
                .any {it.state == NetworkInfo.State.CONNECTED}

        /*for(n in networks){
            val info = connectivity.getNetworkInfo(n)
            if(info.state == NetworkInfo.State.CONNECTED){
                return true
            }
        }
        return false*/
    }


}