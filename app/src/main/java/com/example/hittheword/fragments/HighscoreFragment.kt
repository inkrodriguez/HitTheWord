package com.example.hittheword.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hittheword.R


class HighscoreFragment : Fragment() {

    private val argumentos by navArgs<HighscoreFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_highscore, container, false)

        var btnReload = view.findViewById<ImageView>(R.id.btnReload)

        btnReload.setOnClickListener {
            findNavController().navigate(HighscoreFragmentDirections.actionHighscoreFragmentToMainFragment())
        }

        val tvPontuacaoFinal = view.findViewById<TextView>(R.id.tvHighscore)
        tvPontuacaoFinal.text = argumentos.pontuacaoFinal.toString()


        return view
    }
}