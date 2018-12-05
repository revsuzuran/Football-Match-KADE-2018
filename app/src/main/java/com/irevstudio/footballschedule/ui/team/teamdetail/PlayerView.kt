package com.irevstudio.footballschedule.ui.team.teamdetail

import com.irevstudio.footballschedule.model.Player

interface PlayerView{
    fun showLoading()
    fun hideLoading()
    fun show(data: List<Player>)
}