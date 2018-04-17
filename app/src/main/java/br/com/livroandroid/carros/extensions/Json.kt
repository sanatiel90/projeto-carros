package br.com.livroandroid.carros.extensions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * Created by Sanatiel on 16/04/2018.
 */
//extensao q contem metodos para converter um JSON em objeto e vice versa
//de obj para json, o param prettyPrinting apenas indica se ele sera cria num formato 'bonito' de ler
fun Any.toJson(prettyPrinting: Boolean = false): String{
    //usando lib GSON
    val builder = GsonBuilder()
    if(prettyPrinting){
        builder.setPrettyPrinting()
    }
    //cria json a partir da lib gson
    val json = builder.create().toJson(this)
    return json
}


//converte json para obj
inline fun <reified T> Any.fromJson(json: String): T{
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson<T>(json, type)
}