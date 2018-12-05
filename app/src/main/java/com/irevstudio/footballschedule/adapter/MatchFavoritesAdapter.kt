package com.irevstudio.footballschedule.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.model.MatchFavorites
import com.irevstudio.footballschedule.ui.match.MatchUI
import com.irevstudio.footballschedule.util.DateUtils
import com.irevstudio.footballschedule.util.TimeUtils
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/*
 * Created On : 10/25/18 10:17 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */
class MatchFavoritesAdapter(private val match: List<MatchFavorites>, private val listener: (MatchFavorites) -> Unit)
    : RecyclerView.Adapter<MatchFavoriteViewHolder>(){
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchFavoriteViewHolder {
        return MatchFavoriteViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: MatchFavoriteViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }

}


class MatchFavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val homeName: TextView = view.find(R.id.nextMatch_homeName)
    private val homeSkor: TextView = view.find(R.id.nextMatch_homeSkor)
    private val awayName: TextView = view.find(R.id.nextMatch_awayName)
    private val awaySkor: TextView = view.find(R.id.nextMatch_awaySkor)
    private val dateMatch: TextView = view.find(R.id.nextMatch_date)
    private val timeMatch: TextView = view.find(R.id.nextMatch_time)

    fun bindItem(match: MatchFavorites, listener: (MatchFavorites) -> Unit){

        dateMatch.text = DateUtils.convert(match.matchDate.toString())
        timeMatch.text = TimeUtils.convert(match.matchTime.toString())
        homeName.text = match.homeTeamName
        homeSkor.text = match.homeTeamSkor.toString();if(homeSkor.text == "null"){homeSkor.text = "-"}
        awayName.text = match.awayTeamName
        awaySkor.text = match.awayTeamSkor.toString();if(awaySkor.text == "null"){awaySkor.text = "-"}

        itemView.setOnClickListener {
            listener(match)
        }

    }
}