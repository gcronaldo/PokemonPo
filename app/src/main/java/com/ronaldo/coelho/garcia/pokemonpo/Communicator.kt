package com.ronaldo.coelho.garcia.pokemonpo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class Communicator : ViewModel(){

    val message = MutableLiveData<List<Ranking>>()

    fun setMsgCommunicator(msg:List<Ranking>){
        message.setValue(msg)
    }
}