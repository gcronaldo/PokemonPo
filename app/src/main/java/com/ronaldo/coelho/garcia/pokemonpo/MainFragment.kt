package com.ronaldo.coelho.garcia.pokemonpo

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    var lstRanking = listOf<Ranking>()
    private var model: Communicator? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        requestRanking()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val goAbout = view.findViewById<Button>(R.id.btnAbout)
        val goRanking = view.findViewById<Button>(R.id.btnRanking)
        val playGame = view.findViewById<Button>(R.id.btnPlayGame)

        goAbout.setOnClickListener{
            val aboutFragment = AboutFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, aboutFragment)
            transaction.commit()
        }

        goRanking.setOnClickListener{
            model= ViewModelProviders.of(activity!!).get(Communicator::class.java)
            model!!.setMsgCommunicator(lstRanking)
            val rankingFragment = RankingFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, rankingFragment)
            transaction.commit()
        }

        playGame.setOnClickListener{
            val playGameFragment = PlayGameFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, playGameFragment)
            transaction.commit()
        }

        return view
    }


    private fun requestRanking() {

        Client.instance.retrieveRanking().enqueue(object : Callback<List<Ranking>> {
            override fun onFailure(call: Call<List<Ranking>>, t: Throwable) {
                Log.d("MainFragment", "Return error message from interface: >>>>>> "+t.message)
            }

            override fun onResponse(call: Call<List<Ranking>>, response: Response<List<Ranking>>) {
                if (response.isSuccessful) {
                    if(response.body()!!.isNotEmpty()){
                        lstRanking = response.body()!!
                        Log.d("MainFragment", "Value of list body: >>>>>> "+response.body())
                    }
                }
            }
        })
    }

}
