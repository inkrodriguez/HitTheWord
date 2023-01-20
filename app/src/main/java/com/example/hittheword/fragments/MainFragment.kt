package com.example.hittheword.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hittheword.R
import kotlinx.coroutines.flow.callbackFlow
import org.w3c.dom.Text

class MainFragment : Fragment() {
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.ctx = context

        val editWord = view.findViewById<EditText>(R.id.editWord)
        var tvResultado = view.findViewById<TextView>(R.id.tvResultado)
        val tvMoeda = view.findViewById<TextView>(R.id.tvMoeda)
        val tvVida = view.findViewById<TextView>(R.id.tvVida)
        val btnPlay = view.findViewById<ImageView>(R.id.btnPlay)

        tvResultado.text = viewModel.shuffleName().toString()

        viewModel.player.pontuacao.observe(viewLifecycleOwner, Observer {
            tvMoeda.text = it.toString()
        })

        viewModel.player.life.observe(viewLifecycleOwner, Observer {
            tvVida.text = it.toString()
        })

        viewModel.palavra.resultadoFinal.observe(viewLifecycleOwner, Observer {
            var palavra = it.toString()
            tvResultado.text = palavra
        })

        btnPlay.setOnClickListener {
            viewModel.checkWord("${editWord.text}")
            editWord.text.clear()
            Toast.makeText(context, "${viewModel.returnResult.value}", Toast.LENGTH_SHORT).show()
            gameOver()
        }

        return view

    }






    fun gameOver(){
        if(viewModel.player.vida == 0){
            val pontuacaoFinal: Int = viewModel.player.moeda
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToHighscoreFragment(pontuacaoFinal))
            viewModel.palavra.resetVariables()
            viewModel.player.resetPlayer()

        }

    }

}