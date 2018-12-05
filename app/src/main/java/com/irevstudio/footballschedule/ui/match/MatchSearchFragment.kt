package com.irevstudio.footballschedule.ui.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.irevstudio.footballschedule.adapter.MatchAdapter
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.model.Match
import com.irevstudio.footballschedule.ui.main.MainActivity.Companion.dataSearch
import com.irevstudio.footballschedule.ui.match.detailmatch.MatchDetail
import com.irevstudio.footballschedule.util.inVisible
import com.irevstudio.footballschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.startActivity

class MatchSearchFragment: Fragment(), MatchView{

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar

    companion object {
        lateinit var presenterMatch: MatchPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val ui = UI {
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                listEvent = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                progressBar = progressBar {
                }.lparams {
                    centerHorizontally()
                }
            }

        }

        adapter = MatchAdapter(matches){
            startActivity<MatchDetail>(
                    "idMatch" to it.idMatch, "idHomeTeam" to it.idTeamHome, "idAwayTeam" to it.idTeamAway
            )
        }
        listEvent.adapter = adapter

        val req = ApiRepository()
        val gson = Gson()
        presenterMatch = MatchPresenter(this, req, gson)

        presenterMatch.ambilListSearchEvent(dataSearch)

        return ui.view
    }

    override fun hideLoading() {
        progressBar.inVisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showEvent(data: List<Match>) {
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
