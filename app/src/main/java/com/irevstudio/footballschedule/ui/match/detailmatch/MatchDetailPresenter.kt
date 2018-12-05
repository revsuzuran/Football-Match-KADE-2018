/*
 * Created On : 10/29/18 9:02 AM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.match.detailmatch

import android.util.Log
import com.google.gson.Gson
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.api.FootballDBApi
import com.irevstudio.footballschedule.model.MatchResponse
import com.irevstudio.footballschedule.model.TeamResponse
import com.irevstudio.footballschedule.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(private val view: MatchDetailView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun ambilListEvent(idMatch: String?, idHome: String?, idAway: String?) {

        view.showLoading()
        GlobalScope.launch(context.main){
            val dataMatch = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailMatch(idMatch)).await(),
                    MatchResponse::class.java
            )
            val dataTeamHome = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailteam(idHome)).await(),
                    TeamResponse::class.java
            )
            val dataTeamAway = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailteam(idAway)).await(),
                    TeamResponse::class.java
            )

            Log.d("aqil", FootballDBApi.ambilDetailteam(idAway))
                   Log.e("the data", "$dataTeamHome")
            view.showMatchDetail(dataMatch.events[0], dataTeamHome.teams[0], dataTeamAway.teams[0])
            view.hideLoading()
        }
    }
}