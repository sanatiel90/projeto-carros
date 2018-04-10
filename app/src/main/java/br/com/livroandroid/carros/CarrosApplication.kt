package br.com.livroandroid.carros

import android.app.Application
import android.util.Log

/**
 * Created by Sanatiel on 30/03/2018.
 */
//class Application customizada, é um Singleton usado para definir variaveis e metodos estaticos(de classe)
class CarrosApplication : Application() {

    private val TAG = "CarrosApplication"
    //chamado quando o android criar o processo da aplicacao
    override fun onCreate() {
        super.onCreate()
        //salva a instancia para termos acesso como Singleton
        appInstance = this
    }

    //Kotlin: notacao/objeto para definir variaveis e metodos estaticos
    companion object {
        //Singleton da classe Application
        private var appInstance: CarrosApplication? = null
        fun getInstance(): CarrosApplication{
            if (appInstance == null){
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG,"CarrosApplication.onTerminate()")
    }
}