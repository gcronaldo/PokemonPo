package com.ronaldo.coelho.garcia.pokemonpo

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class RankingViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_ranking_item, parent, false)) {

    private var mPlayerName: TextView? = null
    private var mPoints: TextView? = null

    init {
        mPlayerName = itemView.findViewById(R.id.txtPlayerName)
        mPoints = itemView.findViewById(R.id.txtPointsResult)
    }

    fun bind(ranking: Ranking) {
        mPlayerName?.text = ranking.nome
        mPoints?.text = ranking.pontos.toString()
    }
}