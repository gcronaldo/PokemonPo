package com.ronaldo.coelho.garcia.pokemonpo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_gameover.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class GameOverFragment : Fragment() {

    var playerName: String = ""
    var points: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gameover, container, false)
        val playGame = view.findViewById<Button>(R.id.btnPlayGame)
        val goHome = view.findViewById<Button>(R.id.btnGoHome)

        if(arguments!!.getString("points") != null) {
            points = arguments!!.getString("points").toInt()
        }

        // Goto Game button
        playGame.setOnClickListener{

            playerName = etPlayerName.text.toString()

            if(playerName.isEmpty()) {
                etPlayerName.error = "Por favor, preencha o nome do jogador"
                etPlayerName.requestFocus()
                return@setOnClickListener
            }

            // Make post request to server
            applyRankingRequest()

            val playAgain = PlayGameFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, playAgain)
            transaction.commit()
        }

        // Goto Home button
        goHome.setOnClickListener {
            val home = MainFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, home)
            transaction.commit()
        }

        return view
    }

    private fun applyRankingRequest() {

        val rankingItem = Ranking(playerName, points)

        Client.instance.createPlayerRanking(rankingItem).enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("GAMEOVER", "Return error message from interface: >>>>>> "+t.message)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("GAMEOVER", "Success return message from interface: >>>>>> "+response.body())
                Log.d("GAMEOVER", "Success return code from interface: >>>>>> "+response.code())
            }

        })
    }
}
