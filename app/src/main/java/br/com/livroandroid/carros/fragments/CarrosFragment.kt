package br.com.livroandroid.carros.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.activity.CarroActivity
import br.com.livroandroid.carros.activity.CarrosActivity
import br.com.livroandroid.carros.adapter.CarroAdapter
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.toast
import br.com.livroandroid.carros.utils.AndroidUtils
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CarrosFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CarrosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//herda da BaseFragment criada
class CarrosFragment : BaseFragment() {

    //var privada da Enum TipoCarro,  vai receber uma das enuns enumeradas lá(classicos esportivos ou luxo)
    private var tipo: TipoCarro = TipoCarro.classicos
    //array q vai conter os carros, buscados num web service
    private var carros = listOf<Carro>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //le o paramentro enviado pelo Bundle(vai ser classicos esportivos ou luxo) e converte para TipoCarro
        tipo = arguments.getSerializable("tipo") as TipoCarro
    }

    //cria a view do fragment
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //infla o layout da fragment de carros e o retorna
        val view = inflater?.inflate(R.layout.fragment_carros, container, false)
        return view
        /*
        ANTIGO
        //recupera o txtView presente na fragment(q esta na val view)
        val textView = view?.findViewById<TextView>(R.id.text)
        //converte o R.string.xxx da ENUM informada no parametro em texto
        val tipoString = getString(tipo.string)
        //atribui a ENUM informada ao texto da textView do Fragment
        textView?.text = "Carros $tipoString"
        return view*/
        //return inflater?.inflate(R.layout.fragment_carros, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //views
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskCarros()
    }

    //doAsync: met da lib Anko que executa uma lambda de forma assincrona; executa o cód. dentro dela em uma thread separada (assim como Thread.run())
    //uiThread: met da lib Anko que executa uma lamba na UI Thread; usa um Handler internamente na thread paralela, para poder atualizar os novos dados na Thread principal
    fun taskCarros(){
        //verificar se existe conexao com a net
        if(!AndroidUtils.isNetworkAvaliable(context)){
            toast("Não há conexão com a internet")
        }
        //abre uma thread
        doAsync {
            //busca os carros
            carros = CarroService.getCarros(tipo)
            //atualiza a lista na UI Thread
            uiThread {
                recyclerView.adapter = CarroAdapter(carros) {onClickCarro(it) }
            }

        }

        /*PROCEDIMENTO ANTIGO, Q FAZ REQUISICAO A UM ARQUIVO ESTATICO DA PASTA RAW
        //busca os carros, acessando estaticamente o metodo getCarros do Singleton CarroService

        this.carros = CarroService.getCarros(context, tipo)
        //atualiza a lista, criando um Adapter  (informando um array de carros e uma funcao lambda q trata o evento de click no card do carro)
        recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }
        */
    }

    fun onClickCarro(carro: Carro){
        //chama a act q detalha o carro clicado, o obj do carro clicado eh passado como param pra la
        activity.startActivity<CarroActivity>("carro" to carro)
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
  /*  interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarrosFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): CarrosFragment {
            val fragment = CarrosFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }*/
}// Required empty public constructor
