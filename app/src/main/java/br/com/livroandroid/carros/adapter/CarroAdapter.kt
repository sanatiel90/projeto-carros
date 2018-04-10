package br.com.livroandroid.carros.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_carro.view.*

/**
 * Created by Sanatiel on 07/04/2018.
 */
//class q vai inflar o layout do adapter de carros (uma linha da lista) e fazer a ligacao(bind) com cada carro
//define o construtor q recebe uma list de carros e uma funcao onClick, q possui um obj Carro como param
//retorna um adapter do tipo CarroAdapter, q é uma linha a ser adicionada a um RecyclerView
class CarroAdapter(val carros: List<Carro>, val onClick: (Carro) -> Unit): RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>(){


    //retorna quant de carros na lista
    override fun getItemCount() = this.carros.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CarrosViewHolder {
        //infla a view do layout
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_carro, parent, false)
        //retorna o viewholder q contem todas as views
        val holder = CarrosViewHolder(view)
        return holder
    }

    //faz o bind para atualizar o valor das views com os dados do Carro
    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        //recupera o obj carro
        val carro = carros[position]
        //declara a var view com base no item do holder (item da lista), para facilitar o acesso dos atributos 'nome' e 'img
        val view = holder.itemView
        with(view){
            //atualiza o nome do carro
            tNome.text = carro.nome
            //faz down da foto e mostra progressbar (essa funcao loadUrl foi criada na extension Picasso)
            img.loadUrl(carro.urlFoto, progress)
            //add evento de click na linha
            setOnClickListener { onClick(carro) }
        }

    }

    //ViewHolder com as views, é uma classe interna?
    class CarrosViewHolder(view: View): RecyclerView.ViewHolder(view){
        //salva(inicializa) as views no viewholder
        //NAO SERA INICIALIZADO MAIS AQ, POIS FOI FEITA IMPORTACAO DA KOTLIN EXTENSIONS, ENTAO NAO PRECISA FAZER OS FINDVIEWBYID
        /* var tNome: TextView
        var img: ImageView
        var progress: ProgressBar
        var cardView: CardView
        init {

            tNome = view.findViewById<TextView>(R.id.tNome)
            img = view.findViewById<ImageView>(R.id.img)
            progress = view.findViewById<ProgressBar>(R.id.progress)
            cardView = view.findViewById<CardView>(R.id.card_view)
        }
        */


    }

}