package br.com.livroandroid.carros.fragments

import br.com.livroandroid.carros.activity.CarroActivity
import br.com.livroandroid.carros.adapter.CarroAdapter
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.FavoritosService
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 * Created by Sanatiel on 27/04/2018.
 */
//fragment q vai mostrar a lista de carros favoritados, ela estende do fragment padrao de carros
class FavoritosFragment : CarrosFragment()  {

    override fun taskCarros(){
        //nova thread
        doAsync {
            //busca os carros
            carros = FavoritosService.getCarros()
            //atualiza thread
            uiThread {
                //atualiza o adapter no recyclerView
                recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }
            }

        }
    }

    override fun onClickCarro(carro: Carro) {
        //ao clicar o carro, ir para tela de detalhes
        activity.startActivity<CarroActivity>("carro" to carro)
    }


}