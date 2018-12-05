/*
 * Created On : 10/29/18 9:02 AM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.match.detailmatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.db.database
import com.irevstudio.footballschedule.model.Match
import com.irevstudio.footballschedule.model.MatchFavorites
import com.irevstudio.footballschedule.model.Team
import com.irevstudio.footballschedule.util.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class MatchDetail : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var matchID: String
    private lateinit var homeID: String
    private lateinit var awayID: String
    private lateinit var matchs: Match
    private lateinit var homeLogo: String
    private lateinit var awayLogo: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.title = "NextMatch Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchID = intent.getStringExtra("idMatch")
        homeID = intent.getStringExtra("idHomeTeam")
        awayID = intent.getStringExtra("idAwayTeam")

        favoriteState()
        val req = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, req, gson)
        presenter.ambilListEvent(matchID,homeID,awayID)

        swipe.onRefresh {
            presenter = MatchDetailPresenter(this, req, gson)
            presenter.ambilListEvent(matchID,homeID,awayID)

        }

    }

    override fun showLoading() {
        pgbar_matchdetail.visible()
    }

    override fun hideLoading() {
        pgbar_matchdetail.inVisible()
    }

    override fun showMatchDetail(dataMatch: Match?, dataHome: Team?, dataAway: Team?) {
        matchs = Match(dataMatch?.idMatch,
                dataMatch?.dateEvent,dataMatch?.timeEvent,
                dataMatch?.teamHome, dataMatch?.teamAway,
                dataMatch?.teamHomeScore, dataMatch?.teamAwayScore,
                dataMatch?.idTeamHome, dataMatch?.idTeamAway)
        homeLogo = dataHome?.teamBadge.toString()
        awayLogo = dataAway?.teamBadge.toString()

        swipe.isRefreshing = false
        tanggalEvent.text = DateUtils.convert(dataMatch?.dateEvent.toString())
        ed_namaHome.text = dataMatch?.teamHome
        ed_skorHome.text = dataMatch?.teamHomeScore.toString()
        ed_goalHome.text = dataMatch?.teamHomeGoal.toString().replace(";","\n")
        ed_keeperHome.text = dataMatch?.strHomeLineupGoalkeeper.toString().replace(";","\n")
        ed_defenseHome.text = dataMatch?.strHomeLineupDefense.toString().replace(";","\n")
        ed_midHome.text = dataMatch?.strHomeLineupMid.toString().replace(";","\n")
        ed_forwardHome.text = dataMatch?.strHomeLineupForward.toString().replace(";","\n")
        ed_subsHome.text = dataMatch?.strHomeLineupSubstitutes.toString().replace(";","\n")

        ed_namaAway.text = dataMatch?.teamAway
        ed_skorAway.text = dataMatch?.teamAwayScore.toString()
        ed_goalAway.text = dataMatch?.teamAwayGoal.toString().replace(";","\n")
        ed_keeperAway.text = dataMatch?.strAwayLineupGoalkeeper.toString().replace(";","\n")
        ed_defenseAway.text = dataMatch?.strAwayLineupDefense.toString().replace(";","\n")
        ed_midAway.text = dataMatch?.strAwayLineupMid.toString().replace(";","\n")
        ed_forwardAway.text = dataMatch?.strAwayLineupForward.toString().replace(";","\n")
        ed_subsAway.text = dataMatch?.strAwayLineupSubstitutes.toString().replace(";","\n")

        Picasso.get().load(dataHome?.teamBadge).into(ed_imageHome)
        Picasso.get().load(dataAway?.teamBadge).into(ed_imageAway)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
                if(isFavorite) removeFromFavorites() else addToFavorites()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(MatchFavorites.TABLE_MATCH_FAVORITE)
                    .whereArgs("(MATCH_ID = {id})",
                            "id" to matchID)
            val favorite = result.parseList(classParser<MatchFavorites>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


    private fun addToFavorites(){
        try {
            database.use {
                insert(MatchFavorites.TABLE_MATCH_FAVORITE,
                MatchFavorites.MATCH_ID to matchs.idMatch,
                        MatchFavorites.MATCH_DATE to matchs.dateEvent,
                        MatchFavorites.MATCH_TIME to matchs.timeEvent,
                MatchFavorites.HOME_TEAM_ID to matchs.idTeamHome,
                MatchFavorites.AWAY_TEAM_ID to matchs.idTeamAway,
                MatchFavorites.HOME_TEAM_NAME to matchs.teamHome,
                MatchFavorites.AWAY_TEAM_NAME to matchs.teamAway,
                MatchFavorites.HOME_TEAM_LOGO to homeLogo,
                MatchFavorites.AWAY_TEAM_LOGO to awayLogo,
                MatchFavorites.HOME_TEAM_SKOR to matchs.teamHomeScore,
                MatchFavorites.AWAY_TEAM_SKOR to matchs.teamAwayScore) }
            swipe.snackbar("added to favorites").show()
        }catch (e: SQLiteConstraintException){
            swipe.snackbar(e.localizedMessage).show()
        }
    }
    private fun removeFromFavorites(){
        try {
            database.use { delete(MatchFavorites.TABLE_MATCH_FAVORITE, "(MATCH_ID = {id})",
                    "id" to matchID) }
        }catch (e: SQLiteConstraintException){
            swipe.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }


}