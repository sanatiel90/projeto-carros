package br.com.livroandroid.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : BaseActivity() {

    //var carro: Carro? = null
    //usando tecnica LAZY, q faz com q o obj seja carregado na memoria apenas quando for necessario
    //o operador lazy executa o codigo entre as chaves somente na primeira vez que a var carro é utilizada
    //com o lazy, na linha q declara a var ja é possivel informar o codig q faz a leitura do param
    //usando o lazy nao precisa usar o safe call (?) no objeto carro
    val carro by lazy { intent.getParcelableExtra<Carro>("carro") as Carro }
    //usando getParcelableExtra<Carro> ao inves de serializado, pois a class Carro agora implementa Parcelable, q tem melhor performance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        //le o carro enviado como param (nao precisa aqui, pq foi definido no lazy)
        //carro = intent.getSerializableExtra("carro") as Carro
        //seta o nome do carro como tit da toolbar
        setupToolbar(R.id.toolbar,carro?.nome, true)
        //atualiza os dados na tela
        initViews()
    }

    fun initViews(){
        //variaveis tDesc e img geradas automaticamente pelo Kotlin Extensions
        tDesc.text = carro.desc
        img.loadUrl(carro.urlFoto)
    }
}
