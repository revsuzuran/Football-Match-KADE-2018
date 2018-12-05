/*
 * Created On : 10/25/18 10:03 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.match.lastmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.adapter.MatchAdapter
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.model.Match
import com.irevstudio.footballschedule.ui.match.MatchPresenter
import com.irevstudio.footballschedule.ui.match.MatchView
import com.irevstudio.footballschedule.ui.match.detailmatch.MatchDetail
import com.irevstudio.footballschedule.util.inVisible
import com.irevstudio.footballschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class LastMatch : Fragment(), MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val eventType: String = "prev"
    private val leagueId: String = "4328"

    companion object {
        lateinit var presenterPrev: MatchPresenter
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val ui = UI {

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL

                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(R.color.colorAccent,
                                android.R.color.holo_green_light,
                                android.R.color.holo_orange_light,
                                android.R.color.holo_red_light)

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            listEvent = recyclerView {
                                id = R.id.list_event_lastMatch
                                lparams(width = matchParent, height = wrapContent)
                                layoutManager = LinearLayoutManager(ctx)
                            }

                            progressBar = progressBar {
                            }.lparams {
                                centerHorizontally()
                            }
                        }
                    }
            }

        }

        adapter = MatchAdapter(matches) {
            startActivity<MatchDetail>(
                    "idMatch" to it.idMatch, "idHomeTeam" to it.idTeamHome, "idAwayTeam" to it.idTeamAway
            )
        }
        listEvent.adapter = adapter

        val req = ApiRepository()
        val gson = Gson()
        presenterPrev = MatchPresenter(this, req, gson)
        presenterPrev.ambilListEvent(leagueId, eventType)

        swipeRefresh.onRefresh { presenterPrev.ambilListEvent(leagueId, eventType) }

        return ui.view
    }

    override fun hideLoading() {
        progressBar.inVisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showEvent(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()


    }



}
