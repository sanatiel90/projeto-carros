package br.com.livroandroid.carros.domain

import java.io.Serializable

/**
 * Created by Sanatiel on 07/04/2018.
 */
//class especie de model, usa a interface Serializable para serializar os dados passados de act para act
class Carro : Serializable{
    var id:Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    var urlFoto = ""
    var urlInfo = ""
    var urlVideo = ""
    var latitude = ""
    var longitude = ""

    override fun toString(): String {
        return "Carro(nome='$nome')"
    }
}