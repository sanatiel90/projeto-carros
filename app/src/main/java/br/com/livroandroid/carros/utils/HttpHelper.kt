package br.com.livroandroid.carros.utils

import android.util.Log
import okhttp3.*
import java.io.IOException

/**
 * Created by Sanatiel on 18/04/2018.
 */
//classe estatica/Singleton que usa a lib okhttp para fazer requisicoes HTTP
object HttpHelper {

    private val TAG = "http"
    private val LOG_ON = true
    //define o tipo de arq e o encode
    val JSON = MediaType.parse("application/json; charset=utf-8")
    //instancia de okhttp
    val client = OkHttpClient()

    //GET
    fun get(url: String): String{
        log("HttpHelper.get: $url")
        //cria uma requisicao GET
        val request = Request.Builder().url(url).get().build()
        //faz a req GET e retorna o json de respota
        return getJson(request)
    }

    //POST com json(q vai conter os dados a serem inseridos)
    fun post(url: String, json: String): String{
        log("HttpHelper.post: $url -> $json")
        //transforma o json do param em um JSON a ser enviado na requisicao
        val body = RequestBody.create(JSON, json)
        //cria a requisicao com POST e a envia
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    //POST com params (form-urlencoded) a serem preenchidos com dados de um form(valores vao ser armazenados como chave->valor num Map)
    fun postForm(url: String, params: Map<String,String>): String{
        log("HttpHelper.post: $url -> $params")
        //add os params chave valor na request do post
        val builder = FormBody.Builder()
        for((key, value) in params) {
            builder.add(key, value)
        }
        val body = builder.build()

        //faz a request
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    //DELETE
    fun delete(url:String): String{
        log("HttpHelper.delete: $url")
        val request = Request.Builder().url(url).delete().build()
        return getJson(request)
    }

    //faz a requisicao e le a resposta do servidor no formato json
    private fun getJson(request: Request?): String{
        //faz a requisicao e retorna a resp
        val response = client.newCall(request).execute()
        //pega o corpo da resp
        val responseBody = response.body()
        if(responseBody != null){
            //json com o corpo da resposta
            val json = responseBody.string()
            log("  << : $json")
            return json
        }
        throw IOException("Erro ao fazer a requisição")
    }

    private fun log(s: String){
        if(LOG_ON){
            Log.d(TAG,s)
        }
    }
}