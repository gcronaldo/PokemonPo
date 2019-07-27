package com.ronaldo.coelho.garcia.pokemonpo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_ranking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        val goHome = view.findViewById<Button>(R.id.btnHome)

        goHome.setOnClickListener{
            val home = MainFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, home)
            transaction.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model= ViewModelProviders.of(activity!!).get(Communicator::class.java)
        model.message.observe(this, object : Observer<List<Ranking>> {
            override fun onChanged(o: List<Ranking>?) {
                rvRanking.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = RankingAdapter(o!!)
                }
            }
        })
    }

}
