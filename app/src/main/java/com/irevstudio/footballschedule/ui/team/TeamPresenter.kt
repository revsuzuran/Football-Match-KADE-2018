/*
 * Created On : 10/25/18 10:03 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.team

import com.google.gson.Gson
import com.irevstudio.footballschedule.model.TeamResponse
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.api.FootballDBApi
import com.irevstudio.footballschedule.model.LeagueResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getTeamList(leagueId: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilTeams(leagueId)).await(),
                    TeamResponse::class.java
            )
            view.hideLoading()
            view.showListTeam(data.teams)

        }


    }

    fun getTeamSearchList(teamName: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilSearchTeam(teamName)).await(),
                    TeamResponse::class.java
            )
            view.hideLoading()
            view.showListTeam(data.teams)

        }


    }
}