/*
 * Created On : 10/25/18 10:17 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.model.Match
import com.irevstudio.footballschedule.ui.match.MatchUI
import com.irevstudio.footballschedule.util.DateUtils
import com.irevstudio.footballschedule.util.TimeUtils
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MatchAdapter(private val match: List<Match>,private val listener: (Match) -> Unit)
    :RecyclerView.Adapter<MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(match[position],listener)
    }

}

class MatchViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val homeName: TextView = view.find(R.id.nextMatch_homeName)
    private val homeSkor: TextView = view.find(R.id.nextMatch_homeSkor)
    private val awayName: TextView = view.find(R.id.nextMatch_awayName)
    private val awaySkor: TextView = view.find(R.id.nextMatch_awaySkor)
    private val dateMatch: TextView = view.find(R.id.nextMatch_date)
    private val timeMatch: TextView = view.find(R.id.nextMatch_time)

    fun bindItem(match: Match, listener: (Match) -> Unit){

        dateMatch.text = DateUtils.convert(match.dateEvent.toString())
        timeMatch.text = TimeUtils.convert(match.timeEvent.toString())
        homeName.text = match.teamHome
        homeSkor.text = match.teamHomeScore.toString();if(homeSkor.text == "null"){homeSkor.text = "-"}
        awayName.text = match.teamAway
        awaySkor.text = match.teamAwayScore.toString();if(awaySkor.text == "null"){awaySkor.text = "-"}

        itemView.setOnClickListener {
            listener(match)
        }

    }
}