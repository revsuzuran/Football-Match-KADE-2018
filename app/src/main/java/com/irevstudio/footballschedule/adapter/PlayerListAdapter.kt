package com.irevstudio.footballschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.model.Player
import com.irevstudio.footballschedule.ui.team.PlayerListUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class PlayerListAdapter(private val player: List<Player>, private val listener: (Player) -> Unit )
    : RecyclerView.Adapter<PlayerListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        return PlayerListViewHolder(PlayerListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        holder.bindItem(player[position], listener)
    }
}

class PlayerListViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val playerPicture: ImageView = view.find(R.id.playerPict)
    private val playerName: TextView = view.find(R.id.playerName)

    fun bindItem(player: Player, listener: (Player) -> Unit){

        Picasso.get().load(player.strCutout).into(playerPicture)
        playerName.text = player.strPlayer

        itemView.setOnClickListener {
            listener(player)
        }

    }
}