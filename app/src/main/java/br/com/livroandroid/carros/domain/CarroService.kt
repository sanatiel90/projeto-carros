package br.com.livroandroid.carros.domain

import android.content.Context
import android.util.Log
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.dao.DatabaseManager
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.toJson
import br.com.livroandroid.carros.utils.HttpHelper
import org.json.JSONArray
import java.net.URL

/**
 * Created by Sanatiel on 07/04/2018.
 */
//classe q vai fazer requisicao à api para criar lista de carros
//a palavra object transforma essa class num Singleton
object CarroService {
    private val TAG = "livro" //para os logs LogCat
    //endpoint da API q vai ser acessada
    private val BASE_URL = "http://livrowebservices.com.br/rest/carros"

    //busca os carros por tipo e retorna uma lista de carros
    fun getCarros(tipo: TipoCarro): List<Carro>{
       val url = "$BASE_URL/tipo/${tipo.name}"
       Log.d(TAG,url)
      //fazendo uma requisicao HTTP GET simples e pegando o json retornado
    //   val json = URL(url).readText()
        //fazendo req GET usando okhttp(metodo foi criado na classe utils HttpHelper)
       val json = HttpHelper.get(url)
       val carros = fromJson<List<Carro>>(json)
       Log.d(TAG,"${carros.size} carros encontrados")
       return carros
    }

    //salva um carro
    fun save(carro: Carro): Response{
        //faz um POST do json carro (usando o met toJson q foi criado para converter obj em json)
        val json = HttpHelper.post(BASE_URL, carro.toJson())
        //usando met fromJson q foi criado para converter o json de resposta em obj(nesse caso um obj da classe Response)
        val response = fromJson<Response>(json)
        return response
    }

    //deleta um carro
    fun delete(carro: Carro): Response{
        //informando na url o id a ser deletado
        val url = "$BASE_URL/${carro.id}"
        val json = HttpHelper.delete(url)
        val response = fromJson<Response>(json)
        //se deu certo apagar o carro do WS, apagar tbm no banco sqlite
        if (response.isOk()){
            val dao = DatabaseManager.getCarroDAO()
            dao.delete(carro)
        }
        return response
    }


    /*
    VERSAO PARA USAR LENDO ARQUIVOS JSON ESTATICOS, SEM CONSULTA A WEB SERVICE
    //busca os carros por tipo e retorna uma lista de carros
    fun getCarros(context:Context, tipoCarro: TipoCarro): List<Carro>{
        //arq q vamos ler (de acordo com o tipo)
        val raw = getArquivoRaw(tipoCarro)
        //abre o arq para leitura
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use {
            //le o json e cria uma lista de carros
            val json = it.readText()
            //converte o JSON para ListCarro, atraves do met fromJson<List<Class>>() q foi criado na extensao Json.kt, esse metodo usa a lib Gson
            val carros = fromJson<List<Carro>>(json) //parserJson(json)
            return carros
        }
    }*/

    //le o tipo de carro e retorna o arquivo json q esta na pasta raw
    fun getArquivoRaw(tipo: TipoCarro) = when(tipo){
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

    //caso nao queira usar a lib Gson para converter um json em obj, vc pode usar essa funcao
    fun parserJson(json: String): List<Carro>{
        val carros = mutableListOf<Carro>()
        //cria um array com este json
        val array = JSONArray(json)
        //percorre cada carro
        for (i in 0..array.length() - 1){
            //JSON com as info de cada carro (de acordo com o indice do for)
            val jsonCarro = array.getJSONObject(i)
            //le as informacoes de cada carro e add numa instancia de Carro
            val c = Carro()
            c.nome = jsonCarro.optString("nome")
            c.desc = jsonCarro.optString("desc")
            c.urlFoto = jsonCarro.optString("url_foto")
            //add o carro à lista
            carros.add(c)
        }
        Log.d(TAG,"${carros.size} carros encontrados") //log
        return carros
    }

}