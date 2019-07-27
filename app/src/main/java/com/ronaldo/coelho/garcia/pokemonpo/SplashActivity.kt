package com.ronaldo.coelho.garcia.pokemonpo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(2000)
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }
                catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
