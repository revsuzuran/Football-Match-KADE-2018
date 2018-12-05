package com.irevstudio.footballschedule.ui.team.teamdetail

import com.google.gson.Gson
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.api.FootballDBApi
import com.irevstudio.footballschedule.model.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson){

    fun getTeamDetail(teamId: String?){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailteam(teamId)).await(),
            TeamResponse::class.java
            )
                view.hideLoading()
                view.showTeamDetail(data.teams)

        }
    }
}