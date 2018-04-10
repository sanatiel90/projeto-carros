package br.com.livroandroid.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : BaseActivity() {
    var carro: Carro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        //le o carro enviado como param
        carro = intent.getSerializableExtra("carro") as Carro
        //seta o nome do carro como tit da toolbar
        setupToolbar(R.id.toolbar,carro?.nome, true)
        //atualiza os dados na tela
        initViews()
    }

    fun initViews(){
        //variaveis tDesc e img geradas automaticamente pelo Kotlin Extensions
        tDesc.text = carro?.desc
        img.loadUrl(carro?.urlFoto)
    }
}
