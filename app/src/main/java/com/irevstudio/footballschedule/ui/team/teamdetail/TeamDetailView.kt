package com.irevstudio.footballschedule.ui.team.teamdetail

import com.irevstudio.footballschedule.model.Team

interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}