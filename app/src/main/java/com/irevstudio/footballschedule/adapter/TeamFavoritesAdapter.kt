package com.irevstudio.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.model.TeamFavorites
import com.irevstudio.footballschedule.ui.team.TeamUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find


class FavoriteTeamsAdapter(private val favorite: List<TeamFavorites>, private val listener: (TeamFavorites) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(R.id.logo_tim)
    private val teamName: TextView = view.find(R.id.nama_tim)

    fun bindItem(favorite: TeamFavorites, listener: (TeamFavorites) -> Unit) {
        Picasso.get().load(favorite.teamLogo).into(teamBadge)
        teamName.text = favorite.teamName
        itemView.setOnClickListener { listener(favorite) }
    }
}