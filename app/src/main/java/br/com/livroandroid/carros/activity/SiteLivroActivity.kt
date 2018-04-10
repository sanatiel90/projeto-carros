package br.com.livroandroid.carros.activity

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.activity.dialogs.AboutDialog
import br.com.livroandroid.carros.extensions.setupToolbar
//importando kotlin extensions, com ela vc pode importar o layout xml para a classe, fazendo com q seus componentes fiquem acessiveis
//pelo id q foram declarados la, sem q assim seja preciso ficar fazendo findViewById para recuperar tais componentes
import kotlinx.android.synthetic.main.activity_site_livro.*

class SiteLivroActivity : BaseActivity() {
    //url q sera carregada no webview
    private val URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm"
    //para isstanciar componentes

    /*var webview: WebView? = null
    var progress: ProgressBar? = null
    var swipeToRefresh: SwipeRefreshLayout? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)
        //toolbar
        var actionBar = setupToolbar(R.id.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)

        //recupera as views - nao precisa mais devido a kotlin extensions
        //webview = findViewById<WebView>(R.id.webview)
       // progress = findViewById<ProgressBar>(R.id.progress)

        //carrega a pagina
        setWebViewClient(webview)
        webview.loadUrl(URL_SOBRE)

        //swipe to refresh(recarregar webview ao rolar a tela)
        //swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeToRefresh?.setOnRefreshListener {
            webview.reload() //met q recarrega o webview
        }

        //setando cores da animacao do swipe
        swipeToRefresh?.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3
        )

    }

    //fun para controlar os eventos da view, vai criar um obj WebViewClient, q possui os metodos onPageStarted(que é ativado quando o carregamento
    // da pag é iniciado) e onPageFinished (q é quando pag termina de carregar); nesse caso, entquanto estiver nesse processo de carregamento
    //vai deixar o progressBar visivel, depois q carregar esconde o progressBar
    private fun setWebViewClient(webview: WebView?){
        //nao entendi :/
        // NOTACAO OBJECT EXPRESSIONS( object: ) a classe WebViewClient, caso seja usada, tem varios metodos q precisam ser implementados, porem
        //nesse caso foi feita uma implementacao anonima de WebViewClient, atraves da sintaxe
        // instancia.prop = object : NomeDaClasseImplantada(){ métodos a serem sobrescritos }
        // e assim foram sobrescritos apenas os metodos onPageStarted e onPageFinished; em java isso é conhecido como classes internas INNER CLASS
        webview?.webViewClient = object : WebViewClient() {
            //met da classe WebViewClient
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                //liga o progress
                progress.visibility = View.VISIBLE
            }
            //met da classe WebViewClient
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //desliga o progress
                progress.visibility = View.INVISIBLE

                //termina animacao do swipe
                swipeToRefresh.isRefreshing = false
            }

            //met da classe WebViewClient, intercepta urls enviadas pelo webview
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString() //recupera a url enviada
                //faz alguma coisa se o final da url tiver "sobre.htm"
                if(url.endsWith("sobre.htm")){
                    //mostra alert dialog
                    AboutDialog.showAbout(supportFragmentManager)
                    //retorna true para informar que interceptamos o evento
                    return true
                }

                return super.shouldOverrideUrlLoading(view, request)
            } //shouldOverrideUrlLoading
        } //object WebViewClient


    }
}
