package br.com.livroandroid.carros.domain

import android.content.Context

/**
 * Created by Sanatiel on 07/04/2018.
 */
//classe q vai fazer requisicao à api para criar lista de carros
//a palavra object transforma essa class num Singleton
object CarroService {
    //busca os carros por tipo e retorna uma lista de carros
    fun getCarros(context:Context, tipoCarro: TipoCarro): List<Carro>{
        val tipoString = context.getString(tipoCarro.string)
        //cria array vazio de carros
        val carros = mutableListOf<Carro>()
        //cria 20 carros
        for(i in 1..20){
            val c = Carro()
            c.nome = "Carro $tipoString: $i"
            c.desc = "Desc "+i
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png"
            carros.add(c)
        }
        return carros
    }

}