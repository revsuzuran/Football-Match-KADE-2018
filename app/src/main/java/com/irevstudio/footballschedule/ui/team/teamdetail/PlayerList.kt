package com.irevstudio.footballschedule.ui.team.teamdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.irevstudio.footballschedule.adapter.PlayerListAdapter
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.model.Player
import com.irevstudio.footballschedule.ui.player.PlayerDetailActivity
import com.irevstudio.footballschedule.ui.team.teamdetail.TeamDetailActivity.Companion.dataTeamId
import com.irevstudio.footballschedule.util.inVisible
import com.irevstudio.footballschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI

class PlayerList: Fragment(), PlayerView{


    private var players: MutableList<Player> = mutableListOf()
    private lateinit var playerList: RecyclerView
    private lateinit var adapter: PlayerListAdapter
    private lateinit var presenter: PlayerPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val ui = UI{
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                progressBar = progressBar {
                }.lparams {
                    centerHorizontally()
                }
                playerList = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }

        adapter = PlayerListAdapter(players){
            requireContext().startActivity<PlayerDetailActivity>("id" to "${it.idPlayer}")
        }
        playerList.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerList(dataTeamId)

        return ui.view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.inVisible()
    }


    override fun show(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

}