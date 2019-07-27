package com.ronaldo.coelho.garcia.pokemonpo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class RankingAdapter (private val list: List<Ranking>): RecyclerView.Adapter<RankingViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RankingViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return RankingViewHolder(inflater, p0)
    }

    override fun onBindViewHolder(p0: RankingViewHolder, p1: Int) {
        val ranking: Ranking = list[p1]
        p0.bind(ranking)
    }

    override fun getItemCount(): Int = list.size
}