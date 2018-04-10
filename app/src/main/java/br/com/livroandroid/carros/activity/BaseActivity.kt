package br.com.livroandroid.carros.activity  //pacote em q ficarao todas as act

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Created by Sanatiel on 30/03/2018.
 */
//essa sera a classe base para todas as act. do projeto, ela define os metodos que todas as act. devem ter assim como as properties
//notacao open indica q ela pode ser herdada
@SuppressLint("Registered") //anotacao para evitar msg de q essa act nao esta no manifesto, pois ela de fato nao sera registrada la, servira apenas de base para as demais
open class BaseActivity : AppCompatActivity(){

    //prop para acessar contexto de qq lugar
    protected val context: Context get() = this

}