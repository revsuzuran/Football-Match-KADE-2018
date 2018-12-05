/*
 * Created On : 10/29/18 5:24 AM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.match

import com.irevstudio.footballschedule.model.Match

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showEvent(data: List<Match>)
}