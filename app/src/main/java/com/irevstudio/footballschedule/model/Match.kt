/*
 * Created On : 10/25/18 10:03 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.model

import com.google.gson.annotations.SerializedName

class Match(

        //=============== globalData
        @SerializedName("idEvent")
        var idMatch: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strTime")
        var timeEvent: String? = null,

        @SerializedName("strHomeTeam")
        var teamHome: String? = null,

        @SerializedName("strAwayTeam")
        var teamAway: String? = null,

        @SerializedName("intHomeScore")
        var teamHomeScore: Int? = null,

        @SerializedName("intAwayScore")
        var teamAwayScore: Int? = null,

        @SerializedName("idHomeTeam")
        var idTeamHome: String? = null,

        @SerializedName("idAwayTeam")
        var idTeamAway: String? = null,


        //=============== AWAY TEAM DATA

        //AWAY MATCH STATS

        @SerializedName("strAwayGoalDetails")
        var teamAwayGoal: String? = null,


        //AWAY LINEUPS
        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMid: String? = null,

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = null,

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = null,



        //============ HOME TEAM DATA
        @SerializedName("strHomeGoalDetails")
        var teamHomeGoal: String? = null,

        //HOME LINEUPS
        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = null,

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMid: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = null
)