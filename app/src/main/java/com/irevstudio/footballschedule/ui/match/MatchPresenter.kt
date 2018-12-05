/*
 * Created On : 10/29/18 5:27 AM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.match

import android.util.Log
import com.google.gson.Gson
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.api.FootballDBApi
import com.irevstudio.footballschedule.model.MatchResponse
import com.irevstudio.footballschedule.model.SearchMatchResponse
import com.irevstudio.footballschedule.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun ambilListEvent(leagueId: String?, eventType: String?) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val dataMatch = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilEvent(leagueId,eventType)).await(),
                    MatchResponse::class.java
            )

            Log.d("aqil", FootballDBApi.ambilEvent(leagueId,eventType))
            Log.e("the data", "$dataMatch")

                view.hideLoading()
                view.showEvent(dataMatch.events)

        }
    }

    fun ambilListSearchEvent(teamName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main){
            val dataSearch = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilSearchMatch(teamName)).await(),
                    SearchMatchResponse::class.java
            )

            view.hideLoading()
            view.showEvent(dataSearch.event)

        }
    }
}