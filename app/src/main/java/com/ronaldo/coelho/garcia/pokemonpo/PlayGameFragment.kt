package com.ronaldo.coelho.garcia.pokemonpo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_game.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class PlayGameFragment : Fragment() {

    var playerChoose = "default"
    var machineChoose = "default"
    var totalPoints = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val goHome = view.findViewById<Button>(R.id.btnPlay)
        val fireCard = view.findViewById<ImageButton>(R.id.btnFire)
        val waterCard = view.findViewById<ImageButton>(R.id.btnWater)
        val grassCard = view.findViewById<ImageButton>(R.id.btnGrass)

        goHome.setOnClickListener{
            val mainFragment = MainFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, mainFragment)
            transaction.commit()
        }

        fireCard.setOnClickListener{
            imgPlayer.setImageDrawable(
                ContextCompat.getDrawable(context!!, R.drawable.pokemon_fire)
            )
            playerChoose = "fire"
            machineChoice()
            calculateResult()
        }

        waterCard.setOnClickListener{
            imgPlayer.setImageDrawable(
                ContextCompat.getDrawable(context!!, R.drawable.pokemon_water)
            )
            playerChoose = "water"
            machineChoice()
            calculateResult()
        }

        grassCard.setOnClickListener{
            imgPlayer.setImageDrawable(
                ContextCompat.getDrawable(context!!, R.drawable.pokemon_grass)
            )
            playerChoose = "grass"
            machineChoice()
            calculateResult()
        }


        return view
    }

    private fun machineChoice() {
        val pokemonList = arrayListOf("fire", "water", "grass")
        val random = Random
        val pokemonIndex = random.nextInt(pokemonList.count())
        machineChoose = pokemonList[pokemonIndex]
        Log.d("Game", "Value of machine choosen >>>>>> "+machineChoose)
        if(machineChoose == "fire") {
            imgMachine.setImageDrawable(
                ContextCompat.getDrawable(context!!, R.drawable.pokemon_fire)
            )
        }
        else if (machineChoose == "water" ) {
            imgMachine.setImageDrawable(
                ContextCompat.getDrawable(context!!, R.drawable.pokemon_water)
            )
        }
        else if (machineChoose == "grass") {
            imgMachine.setImageDrawable(
                ContextCompat.getDrawable(context!!, R.drawable.pokemon_grass)
            )
        }
    }

    private fun calculateResult() {
        if(playerChoose == "water" && machineChoose == "fire") {
            totalPoints = totalPoints + 2
            txtTotalPoints.text = totalPoints.toString()
            showResult("success")
        }
        else if(playerChoose == "fire" && machineChoose == "grass") {
            totalPoints = totalPoints + 2
            txtTotalPoints.text = totalPoints.toString()
            showResult("success")
        }
        else if(playerChoose == "grass" && machineChoose == "water") {
            totalPoints = totalPoints + 2
            txtTotalPoints.text = totalPoints.toString()
            showResult("success")
        }
        else if(playerChoose == machineChoose) {
            totalPoints = totalPoints + 1
            txtTotalPoints.text = totalPoints.toString()
            showResult("equal")
        }
        else {
            showResult("fail")
        }
    }

    private fun showResult(resultGame : String) {
        txtResultMsg.setTextColor(ContextCompat.getColor(context!!, R.color.colorBackgroundButton))
        if(resultGame == "success") {
            txtResultMsg.text = getString(R.string.labelResultWin)
        }
        else if(resultGame == "equal") {
            txtResultMsg.text = getString(R.string.labelResulDraw)
        }
        else {
            txtResultMsg.text = getString(R.string.labelResultLost)
            val gameOverFragment = GameOverFragment()
            val bundle = Bundle()
            bundle.putString("points", totalPoints.toString())
            gameOverFragment.arguments = bundle
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, gameOverFragment)
            //Thread.sleep(3_000)
            transaction.commit()
        }
    }

}
