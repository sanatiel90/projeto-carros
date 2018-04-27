package br.com.livroandroid.carros.domain.dao

import android.arch.persistence.room.Room
import br.com.livroandroid.carros.CarrosApplication

/**
 * Created by Sanatiel on 26/04/2018.
 */
//class singleton q gerencia a classe CarrosDatabase; como é um singleton, vai prover um acesso global ao banco de dados do app
object DatabaseManager {
    //singleton do Room: banco de dados
    private var dbInstance: CarrosDatabase
    init {
        val appContext = CarrosApplication.getInstance().applicationContext
        //configura o room
        dbInstance = Room.databaseBuilder(
                appContext,                 //contexto
                CarrosDatabase::class.java,     //@DAO
                "carros.sqlite"         //nome do BD
        ).build()
    }

    //retorna uma instancia de CarroDAO de forma global
    fun getCarroDAO():CarroDAO{
        return dbInstance.carroDAO()
    }

}