package br.com.livroandroid.carros.domain

import br.com.livroandroid.carros.R

/**
 * Created by Sanatiel on 01/04/2018.
 */
//enum é uma classe/objeto que define constantes a serem usadas no projeto; essa ira definir os tipos de carros, recebendo um inteiro no constutor
//inteiro q é a identificacao dos carros no strings.xml
enum class TipoCarro(val string: Int) {
    classicos(R.string.classicos),
    esportivos(R.string.esportivos),
    luxo(R.string.luxo)


}