package br.com.livroandroid.carros.domain

import br.com.livroandroid.carros.domain.dao.DatabaseManager

/**
 * Created by Sanatiel on 27/04/2018.
 */
//singleton q usa a CarrosDAO para consultar e salvar carros favoritados
object FavoritosService {

    //retorna todos os carros favoritados
    fun getCarros(): List<Carro>{
        //recupera obj dao
        val dao = DatabaseManager.getCarroDAO()
        //retorna todos carros q foram favoritados
        val carros = dao.findAll()
        return carros
    }

    //verifica se um carro esta favoritado
    fun isFavorito(carro: Carro): Boolean{
        val dao = DatabaseManager.getCarroDAO()
        val exists = dao.getById(carro.id) != null
        return exists
    }

    //salva o carro ou deleta
    fun favoritar(carro: Carro): Boolean{
        val dao = DatabaseManager.getCarroDAO()
        val favorito = isFavorito(carro)
        if(favorito){
            //se ele ja estiver favoritado, desfavoritar
            dao.delete(carro)
            return false
        }
       //se nao estiver nos favoritos, favoritar
        dao.insert(carro)
        return true
    }




}