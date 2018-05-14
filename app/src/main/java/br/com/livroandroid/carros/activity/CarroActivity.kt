package br.com.livroandroid.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.FavoritosService
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.jetbrains.anko.*

class CarroActivity : BaseActivity() {

    //var carro: Carro? = null
    //usando tecnica LAZY, q faz com q o obj seja carregado na memoria apenas quando for necessario
    //o operador lazy executa o codigo entre as chaves somente na primeira vez que a var carro é utilizada
    //com o lazy, na linha q declara a var ja é possivel informar o codig q faz a leitura do param
    //usando o lazy nao precisa usar o safe call (?) no objeto carro
    val carro by lazy { intent.getParcelableExtra<Carro>("carro") }
    //usando getParcelableExtra<Carro> ao inves de serializado, pois a class Carro agora implementa Parcelable, q tem melhor performance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        //le o carro enviado como param (nao precisa aqui, pq foi definido no lazy)
        //carro = intent.getSerializableExtra("carro") as Carro
        //seta o nome do carro como tit da toolbar
        setupToolbar(R.id.toolbar,carro.nome, true)
        //atualiza os dados na tela
        initViews()

        //evento botao fab coracao; para favoritar
        fab.setOnClickListener { onClickFavoritar(carro) }
    }

    fun initViews(){
        //variaveis tDesc e img geradas automaticamente pelo Kotlin Extensions
        tDesc.text = carro.desc
        appBarImg.loadUrl(carro.urlFoto) //usando imageview do cabeçalho da AppBarLayout do xml referente ao detalhamento de carros
    }

    //inflando(transf de xml para obj) menu para editar ou deletar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_carro, menu)
        return true
    }

    //eventos do menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            //menu editar chamada a tela de editar e fecha a tela de detalhamento
            R.id.action_editar -> {
                startActivity<CarroFormActivity>("carro" to carro)
                finish()
            }

            R.id.action_deletar -> {
                //class alert da lib anko para confirmar a deleção
                alert(R.string.msg_confirm_excluir_carro, R.string.app_name){
                    positiveButton(R.string.sim){
                        //confirmou o excluir
                        taskExcluir()
                    }
                    negativeButton(R.string.nao){
                        //nao confirmou, nao acontece nada
                    }

                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //funcao q exclui carro do ws e fecha a tela
    fun taskExcluir(){
        doAsync {
            val response = CarroService.delete(carro)
            uiThread {
                toast(response.msg)
                finish()
            }
        }
    }

    //add ou remove carro dos favort
    fun onClickFavoritar(carro: Carro){
        doAsync {
            val favoritado = FavoritosService.favoritar(carro)
            uiThread {
                //alerta de sucesso
                toast(if(favoritado) R.string.msg_carro_favoritado else R.string.msg_carro_desvaforitado)
            }
        }
    }
}
