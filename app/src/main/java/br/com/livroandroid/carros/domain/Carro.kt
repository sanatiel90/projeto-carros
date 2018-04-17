package br.com.livroandroid.carros.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Sanatiel on 07/04/2018.
 */
//class especie de model, usa a interface Serializable para serializar os dados passados de act para act
//ao inves da Serializable, sera usada a Parcelable, q segundo a Google é mais performatica no caso de passar parametros grandes para outras act
class Carro() : Parcelable{
    var id:Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    var urlFoto = ""
    var urlInfo = ""
    //se o nome do campo equivalente no web service ou arquivo estatico estiver diferente do declarado na classe, utilize essa notacao pra indicar
    //qual o nome que esta configurado no web service/arquivo
    //@SerializedName("url_video")
    var urlVideo = ""
    var latitude = ""
    var longitude = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        tipo = parcel.readString()
        nome = parcel.readString()
        desc = parcel.readString()
        urlFoto = parcel.readString()
        urlInfo = parcel.readString()
        urlVideo = parcel.readString()
        latitude = parcel.readString()
        longitude = parcel.readString()
    }

    override fun toString(): String {
        return "Carro(nome='$nome')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //escreve os dados para serem serializados
        parcel.writeLong(id)
        parcel.writeString(tipo)
        parcel.writeString(nome)
        parcel.writeString(desc)
        parcel.writeString(urlFoto)
        parcel.writeString(urlInfo)
        parcel.writeString(urlVideo)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
    }

 
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Carro> {
        override fun createFromParcel(parcel: Parcel): Carro {
            return Carro(parcel)
        }

        override fun newArray(size: Int): Array<Carro?> {
            return arrayOfNulls(size)
        }
    }
}