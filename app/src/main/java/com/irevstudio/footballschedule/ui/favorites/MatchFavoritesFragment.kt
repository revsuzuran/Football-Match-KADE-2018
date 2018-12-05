package com.irevstudio.footballschedule.ui.favorites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.adapter.MatchFavoritesAdapter
import com.irevstudio.footballschedule.db.database
import com.irevstudio.footballschedule.model.MatchFavorites
import com.irevstudio.footballschedule.ui.match.detailmatch.MatchDetail
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFavoritesFragment: Fragment(){

    private var matchs: MutableList<MatchFavorites> = mutableListOf()
    private lateinit var adapter: MatchFavoritesAdapter
    private lateinit var listMatchs: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MatchFavoritesAdapter(matchs) {
            startActivity<MatchDetail>(
                    "idMatch" to it.matchId, "idHomeTeam" to it.homeTeamId, "idAwayTeam" to it.awayTeamId
            )
        }

        listMatchs.adapter = adapter
        showFavorites()
        swipeRefresh.onRefresh {
            matchs.clear()
            showFavorites()
        }

    }

    private fun showFavorites(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val dbMatch = select(MatchFavorites.TABLE_MATCH_FAVORITE)
            val dataMatch = dbMatch.parseList(classParser<MatchFavorites>())
            matchs.addAll(dataMatch)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listMatchs = recyclerView {
                    id = R.id.list_event_favorites
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}