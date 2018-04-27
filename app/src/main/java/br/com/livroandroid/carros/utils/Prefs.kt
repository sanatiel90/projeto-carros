package br.com.livroandroid.carros.utils

import android.content.SharedPreferences
import br.com.livroandroid.carros.CarrosApplication

/**
 * Created by Sanatiel on 26/04/2018.
 */
//class/singleton que encapsula as chamadas a classe SharedPreferences, q é responsavel por salvar valores pequenos de chave->valor no banco de dados nativo do android
object Prefs {

    val PREF_ID = "carros"
    //retorna instancia de SharedPreferences
    private fun prefs(): SharedPreferences{
        //pega contexto da aplicacao com base em CarrosApplication e retorna um SharedPreferences
        val context = CarrosApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID,0)
    }

    //adiciona chave->valor ao SharedPreferences, sendo a chave String e valor int
                //no livro: flag
    fun setInt(chave:String, valor: Int){
        //instancia de SharedPreferences
        val pref = prefs()
        //coloca em modo edicao
        val editor = pref.edit()
        //atribui a chave-valor
        editor.putInt(chave, valor)
        editor.apply()
    }

    //recupera valor Int com base na chave(flag) informada
    fun getInt(chave:String): Int{
        val pref = prefs()
        //recupera o valor int (0 se nao tiver encontrado)
        val i = pref.getInt(chave, 0)
        return i
    }

    //o msm do anterior, as agora atribui uma string
    fun setString(chave:String, valor: String){
        val pref = prefs()
        val editor = pref.edit()
        editor.putString(chave, valor)
        editor.apply()
    }

    fun getString(chave:String):String{
        val pref = prefs()
        val s = pref.getString(chave,"")
        return s
    }










}