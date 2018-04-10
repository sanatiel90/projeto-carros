package br.com.livroandroid.carros.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.setupToolbar

import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

//alem herdar da BaseActivity, implementa a interface NavigationView.OnNavigationItemSelectedListener , q define metodo para usar o NavDrawer
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar) //setando a toolbar na tela inicial
        setupNavDrawer()
    }

    //met para setar um NavDrawer
    private fun setupNavDrawer(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toogle = ActionBarDrawerToggle(
                this,drawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

    }

    //trata o evento do NavDrawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item_carros_todos -> {
                toast("Carros") //toast da lib ANKO
            }
            R.id.nav_item_carros_classicos -> {
                /*FORMA COMUM
                //instanciando intent da act CarrosActivity
                val intent = Intent(context, CarrosActivity::class.java)
                //informando como para a chave "tipo", com key: ENUM com o tipo de carro escolhido
                intent.putExtra("tipo",TipoCarro.classicos)
                startActivity(intent)*/
                //USANDO A LIB ANKO
                startActivity<CarrosActivity>("tipo" to TipoCarro.classicos)
            }
            R.id.nav_item_carros_esportivos -> {
                //USANDO A LIB ANKO para chamar activity
                startActivity<CarrosActivity>("tipo" to TipoCarro.esportivos)
            }
            R.id.nav_item_carros_luxo -> {
                //USANDO A LIB ANKO para chamar activity
                startActivity<CarrosActivity>("tipo" to TipoCarro.luxo)
            }
            R.id.nav_item_site_livro -> {
                startActivity<SiteLivroActivity>()
            }
            R.id.nav_item_settings -> {
                toast("Settings")
            }
        }//fim when
        //fecha o menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    } //fim onNavigationItemSelected
}
