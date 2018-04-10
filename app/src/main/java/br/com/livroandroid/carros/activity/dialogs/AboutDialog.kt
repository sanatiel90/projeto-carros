package br.com.livroandroid.carros.activity.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import br.com.livroandroid.carros.R

/**
 * Created by Sanatiel on 05/04/2018.
 */
//classe q cria dialog para tela Sobre
class AboutDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //cria o HTML com txt sobre o app
        val aboutBody = SpannableStringBuilder()
        //versao do app
        val versioName = getAppVersionName()
        //converte  o txt do strings.xml para html
        val html = Html.fromHtml(getString(R.string.about_dialog_text, versioName))
        aboutBody.append(html)
        //infla o layout
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_about, null) as TextView
        view.text = aboutBody
        view.movementMethod = LinkMovementMethod()
        //cria dialog customizado
        /*return AlertDialog.Builder(activity)
                .setTitle(R.string.about_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                .create()*/
        return AlertDialog.Builder(activity)
                .setTitle("Titulo")
                .setMessage("mensagem")
                .setPositiveButton("OK"){
                    dialog, whichButton -> dialog.dismiss()
                }.create()

        //return super.onCreateDialog(savedInstanceState)
    }

    fun getAppVersionName(): String{
        val pm = activity.packageManager
        val packageName = activity.packageName
        var versionName: String
        try {
          val info = pm.getPackageInfo(packageName, 0)
            versionName = info.versionName
        }catch (ex: PackageManager.NameNotFoundException){
            versionName = "N/A"
        }
       return versionName
    }

    companion object {
        //metodo utilitario para mostrar o dialog
        fun showAbout(fm: FragmentManager){
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag("about_dialog")
            if(prev != null){
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            AboutDialog().show(ft, "about_dialog")
        }
    }

}