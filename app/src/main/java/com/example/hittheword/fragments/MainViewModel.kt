package com.example.hittheword.fragments

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.hittheword.R


class MainViewModel: ViewModel() {

    var ctx: Context? = null
    var returnResult = MutableLiveData<String>()
    var palavra = Palavra()
    var player = Player()

    class Player {

        var moeda = 0
        var vida = 3
        var life = MutableLiveData<Int>()
        var pontuacao = MutableLiveData<Int>()

            fun receiveCoin(){
                moeda += 1
                pontuacao.value = moeda
            }

            fun loseLife(){
                vida -= 1
                life.value = vida
            }

            fun resetPlayer(){
                if(vida == 3){
                    vida = 0
                } else {
                    vida += 3
                }
                life.value = vida
                moeda = 0
                pontuacao.value = moeda
            }
    }

    class Palavra {

        var nameRandom: String = ""
        var nameSplit = mutableListOf("")
        var nameSplitRandom: String = ""
        var resultadoFinal = MutableLiveData<String>()

        var emptyList = mutableListOf<String>()
        var nameList = mutableListOf(
            "CARRO",
            "ABACAXI",
            "PEPINO",
            "MELANCIA",
            "JACA",
            "COPO",
            "JANELA",
            "SAPATO",
            "VERDE"
        )


        fun randomName() {
            nameRandom = nameList.random().uppercase()
        }

        fun splitName() {
            nameSplit = nameRandom.split("") as MutableList<String>
        }

        fun splitNameRandom() {
            nameSplitRandom = nameSplit.random()
        }

        fun createWord(){
            var i = 0
            while (i <= nameRandom.length + 1) {
                splitNameRandom()
                emptyList.add(nameSplitRandom)
                nameSplit.remove(nameSplitRandom)
                i++
            }
        }

        fun joinString(){
            resultadoFinal.value = emptyList.joinToString("")
        }

        fun resetVariables(){
            nameSplitRandom = ""
            nameRandom = ""
            nameSplit.clear()
            emptyList.clear()
        }

        fun removeWordList(){
            nameList.remove(nameRandom)
        }


    }

    fun shuffleName() {
            palavra.randomName()
            palavra.splitName()
            palavra.splitNameRandom()
            palavra.createWord()
            palavra.joinString()
    }

    fun checkWord(editWord: String){
        if(editWord.uppercase() == palavra.nameRandom){
            palavra.removeWordList()
            palavra.resetVariables()
            shuffleName()
            returnResult.value = "Parabéns, você acertou!"
            player.receiveCoin()
        } else {
            returnResult.value = "Opa, acho que não!"
            player.loseLife()
        }
    }



}
