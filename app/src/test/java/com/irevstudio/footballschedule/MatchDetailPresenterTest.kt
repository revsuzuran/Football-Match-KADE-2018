package com.irevstudio.footballschedule

import com.google.gson.Gson
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.api.FootballDBApi
import com.irevstudio.footballschedule.model.Match
import com.irevstudio.footballschedule.model.MatchResponse
import com.irevstudio.footballschedule.model.Team
import com.irevstudio.footballschedule.model.TeamResponse
import com.irevstudio.footballschedule.ui.match.detailmatch.MatchDetailPresenter
import com.irevstudio.footballschedule.ui.match.detailmatch.MatchDetailView
import com.irevstudio.footballschedule.util.TestContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    private
    lateinit var view: MatchDetailView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchDetails() {
        val match: MutableList<Match> = mutableListOf()
        val teams: MutableList<Team> = mutableListOf()
        val responseTeam = TeamResponse(teams)
        val responseMatch = MatchResponse(match)
        val idMatch = "441613"
        val idTeamHome = "133602"
        val idTeamAway = "133614"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailMatch(idMatch)).await(),
                    MatchResponse::class.java
            )).thenReturn(responseMatch)

            `when`(gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailteam(idTeamHome)).await(),
                    TeamResponse::class.java
            )).thenReturn(responseTeam)

            `when`(gson.fromJson(apiRepository
                    .doRequest(FootballDBApi.ambilDetailteam(idTeamAway)).await(),
                    TeamResponse::class.java
            )).thenReturn(responseTeam)

            presenter.ambilListEvent(idMatch,idTeamHome,idTeamAway)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showMatchDetail(match[0],teams[0],teams[0])

        }
    }
}