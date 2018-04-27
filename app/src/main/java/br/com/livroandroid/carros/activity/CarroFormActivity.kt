package br.com.livroandroid.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.isEmpty
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import br.com.livroandroid.carros.extensions.string
import kotlinx.android.synthetic.main.activity_carro_form.*
import kotlinx.android.synthetic.main.activity_carro_form_contents.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

//class responsavel por criar um novo carro(adicionar no web service)
class CarroFormActivity : BaseActivity() {
    //carro pode ser nulo no caso de um novo carro, ou não nulo no caso de editar o carro
    val carro: Carro? by lazy { intent.getParcelableExtra<Carro>("carro") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro_form)
        //titulo da toolbar: novo carro ou o nome do carro a editar
        setupToolbar(R.id.toolbar, carro?.nome?: getString(R.string.novo_carro))

        //atualiza os dados do form
        initViews()
    }

    //inicializa as views
    fun initViews(){
        //a funcao apply somente é execut se o obj nao for nulo; funciona commo um with()
        //mostrando dados do carro a ser editado no radioButton e edittexts
        carro?.apply {
            //Foto do carro
            appBarImg.loadUrl(carro?.urlFoto)
            //dados do carro
            tDesc.string = desc
            tNome.string = nome
            //tipo do carro
            when(tipo){
                "classicos" -> radioTipo.check(R.id.tipoClassico)
                "esportivos" -> radioTipo.check(R.id.tipoEsportivo)
                "luxo" -> radioTipo.check(R.id.tipoLuxo)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            //salva novo carro
            R.id.action_salvar -> taskSalvar()
        }
        return super.onOptionsItemSelected(item)
    }

    //salva novo carro no WS
    fun taskSalvar(){
        if(tNome.isEmpty()){
            //valida se o campo foi preenchdio
            tNome.error = getString(R.string.msg_error_form_nome)
            return
        }

        if(tDesc.isEmpty()){
            tDesc.error = getString(R.string.msg_error_form_desc)
            return
        }

        //cria nova thread
        doAsync {
            //cria um carro para salvar/atualizar
            //operador Elvis ?: -> c vai receber o Carro a ser editado, ou vai ser instanciado novo carro se nao tiver sido passado um como param
            val c = carro?: Carro()
            //copia valores do form para o carro
            c.nome = tNome.string
            c.desc = tDesc.string
            c.tipo = when (radioTipo.checkedRadioButtonId){
                R.id.tipoClassico -> TipoCarro.classicos.name
                R.id.tipoEsportivo -> TipoCarro.esportivos.name
                else -> TipoCarro.luxo.name
            }

            //salva o carro no servidor
            val response = CarroService.save(c)
            //Handler dentro da thread paralela, para mostrar na thread principal
            uiThread {
                //mensagem com resposta do servidor
                toast(response.msg)
                //fecha a tela
                finish()
            }

        }
    }




}
