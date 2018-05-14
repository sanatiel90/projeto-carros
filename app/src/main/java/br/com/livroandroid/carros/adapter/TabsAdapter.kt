package br.com.livroandroid.carros.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.fragments.CarrosFragment
import br.com.livroandroid.carros.fragments.FavoritosFragment

/**
 * Created by Sanatiel on 12/04/2018.
 */
//class q sera o adapter pra alimentar o viewpager (um menu q desliza horizontalmente)
class TabsAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    //define a quant de tabs
    override fun getCount(): Int {
        return 4
    }

    //retorna o tipo de carro pela posicao da tab(Tipos pegues da enum TipoCARRO)
    fun getTipoCarro(position: Int) = when(position){
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        2 -> TipoCarro.luxo
        else -> TipoCarro.favoritos
    }
    //getPageTitle: retorna o titulo da pag, q nesse caso sera o titulo da tab
    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    //retorna o fragment da pag(tab) selecionada; esta sendo usado o fragment q vai mostrar a lista de carros
    override fun getItem(position: Int): Fragment {
        //tab de favoritos
        if(position == 3){
            return FavoritosFragment()
        }
        //demais tabs
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        f.arguments = Bundle()
        f.arguments.putSerializable("tipo", tipo)
        return f
    }
}