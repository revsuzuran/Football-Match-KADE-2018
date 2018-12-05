package com.irevstudio.footballschedule.ui.team.teamdetail

import android.util.Log
import com.google.gson.Gson
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.api.FootballDBApi
import com.irevstudio.footballschedule.model.PlayerDetailResponse
import com.irevstudio.footballschedule.model.PlayerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(private val view: PlayerView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson){

    fun getDetailPlayer(playerId: String?){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val detailPlayer = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailPlayer(playerId)).await(),
                    PlayerDetailResponse::class.java
            )
            Log.d("presenter", "data"+detailPlayer.players)
            view.hideLoading()
            view.show(detailPlayer.players)

        }
    }

    fun getPlayerList(teamId: String?){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilListPlayer(teamId)).await(),
                    PlayerResponse::class.java
            )
            view.hideLoading()
            view.show(data.player)

        }
    }


}