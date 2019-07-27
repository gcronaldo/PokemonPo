package com.ronaldo.coelho.garcia.pokemonpo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_about, container, false)

        val goHome = view.findViewById<Button>(R.id.btnHome)

        goHome.setOnClickListener{
            val mainFragment = MainFragment()
            val manager = fragmentManager
            val transaction = manager!!.beginTransaction()
            transaction.replace(R.id.mainFragment, mainFragment)
            transaction.commit()
        }

        return view
    }

}
