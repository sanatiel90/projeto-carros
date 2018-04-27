package br.com.livroandroid.carros.domain.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.livroandroid.carros.domain.Carro

/**
 * Created by Sanatiel on 26/04/2018.
 */
//classe q vai gerenciar o banco de dados Room; ela define uma lista com todas as entidades que precisam
//ser persistidas, o nome e a versao do bd
//anotation @Database: lista (array) de entidades(classes mapeadas com @Entity) q devem ser persistidas e versao
//a versao é usada para controlar as tabelas do banco quando vc precisar fazer algum ALTER TABLE(pode funcionar como migration ou script de alteracao do banco)
@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase: RoomDatabase() {
    abstract fun carroDAO(): CarroDAO
}