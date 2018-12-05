/*
 * Created On : 10/29/18 9:03 AM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.match.detailmatch

import com.irevstudio.footballschedule.model.Match
import com.irevstudio.footballschedule.model.Team

interface MatchDetailView{

    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(dataMatch: Match?, dataHome: Team?, dataAway: Team?)
}