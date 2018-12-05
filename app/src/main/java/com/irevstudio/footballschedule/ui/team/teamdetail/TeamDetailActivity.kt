/*
 * Created On : 11/1/18 10:50 AM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 */

package com.irevstudio.footballschedule.ui.team.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.R.drawable.ic_add_to_favorites
import com.irevstudio.footballschedule.R.drawable.ic_added_to_favorites
import com.irevstudio.footballschedule.adapter.MatchTabAdapter
import com.irevstudio.footballschedule.adapter.PlayerListAdapter
import com.irevstudio.footballschedule.adapter.TeamDetailTabAdapter
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.db.database
import com.irevstudio.footballschedule.model.Team
import com.irevstudio.footballschedule.model.TeamFavorites
import com.irevstudio.footballschedule.util.inVisible
import com.irevstudio.footballschedule.util.visible
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var viewPager: ViewPager

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    companion object {
        lateinit var dataTeamId: String
        lateinit var dataTeamDesc: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_detail_layout)

        val intent = intent
        id = intent.getStringExtra("id").toString()
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = find(R.id.teamDetailViewPager)
        progressBar = find(R.id.pgBar_teamMatch)
        swipeRefresh = find(R.id.swipeTeamDetail)
        teamBadge = find(R.id.teamsDetailLogo)
        teamName = find(R.id.teamsDetailNama)
        teamFormedYear = find(R.id.teamsDetailYear)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

        swipeRefresh.onRefresh {
            presenter.getTeamDetail(id)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.inVisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        dataTeamId = data[0].teamId.toString()
        teams = Team(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamFormedYear.text = data[0].teamFormedYear
        dataTeamDesc = data[0].teamDescription.toString()
        viewPager.adapter = TeamDetailTabAdapter(this.supportFragmentManager)


    }

    private fun favoriteState(){
        database.use {
            val result = select(TeamFavorites.TABLE_TEAM_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<TeamFavorites>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(TeamFavorites.TABLE_TEAM_FAVORITE,
                        TeamFavorites.TEAM_ID to teams.teamId,
                        TeamFavorites.TEAM_NAME to teams.teamName,
                        TeamFavorites.TEAM_BADGE to teams.teamBadge)
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(TeamFavorites.TABLE_TEAM_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
            }
            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }



}
