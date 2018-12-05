/*
 * Created On : 10/25/18 10:03 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.team

import com.irevstudio.footballschedule.model.LeaguesItem
import com.irevstudio.footballschedule.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showListTeam(data: List<Team>)
}