package br.com.livroandroid.carros.domain.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.livroandroid.carros.domain.Carro

/**
 * Created by Sanatiel on 26/04/2018.
 */
//interface DAO q define as operacoes CRUD q serao feitas na tabela carro
//usando anotation do Room para indicar q sera um DAO
@Dao
interface CarroDAO {
    //para instrucoes select, usar a annotation @Query
    @Query("SELECT * FROM carro")  //anotation q indica qual qry essa funcao vai fazer
    fun findAll(): List<Carro>

    @Query("SELECT * FROM carro WHERE id = :arg0")
    fun getById(id: Long): Carro?

    //para insert e delete, tem a propria annotatiion
    @Insert
    fun insert(carro: Carro)

    @Delete
    fun delete(carro: Carro)

}