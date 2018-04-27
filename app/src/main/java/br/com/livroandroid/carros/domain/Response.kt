package br.com.livroandroid.carros.domain

/**
 * Created by Sanatiel on 18/04/2018.
 */
//data class responsavel por armazenar o retorno das requisicoes feitas ao web service
class Response (val id: Long, val status: String, val msg: String, val url:String){
  //met para descobrir a req à api foi feita com sucesso, retorna true se "OK" for igual ao status do retorno(que pode ser OK ou erro)
    fun isOk() = "OK".equals(status, ignoreCase = true)

}