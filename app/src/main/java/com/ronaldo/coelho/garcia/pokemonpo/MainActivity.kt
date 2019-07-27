package com.ronaldo.coelho.garcia.pokemonpo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.mainFragment, MainFragment())
        transaction.commit()
    }

}
