package com.irevstudio.footballschedule.ui.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.irevstudio.footballschedule.adapter.TeamAdapter
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.model.Team
import com.irevstudio.footballschedule.ui.main.MainActivity.Companion.dataSearch
import com.irevstudio.footballschedule.ui.team.teamdetail.TeamDetailActivity
import com.irevstudio.footballschedule.util.inVisible
import com.irevstudio.footballschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI

class TeamSearchFragment: Fragment(), TeamView{

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val ui = UI {
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                listTeam = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                progressBar = progressBar {
                }.lparams {
                    centerHorizontally()
                }
            }

        }
        adapter = TeamAdapter(teams) {
            requireContext().startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        presenter.getTeamSearchList(dataSearch)

        return ui.view

    }

    override fun showLoading() {
        progressBar.visible()

    }

    override fun hideLoading() {
        progressBar.inVisible()
    }

    override fun showListTeam(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
