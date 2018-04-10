package br.com.livroandroid.carros.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.addFragment
import br.com.livroandroid.carros.extensions.setupToolbar
import br.com.livroandroid.carros.fragments.CarrosFragment

class CarrosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)
        //argumentos: recebe o tipo do carro
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        //titulo vai ser o que estiver no strings.xml do tipo enviado
        val title = getString(tipo.string)
        //toolbar: atribuindo a toolbar, o titulo e o up navigation
        setupToolbar(R.id.toolbar, title, true)
        if (savedInstanceState == null){
            //add o fragment de layout de marcacao
            //dentre os args q foram passados para a act, esta o tipo de carro
            addFragment(R.id.container, CarrosFragment())
        }

    }
}
