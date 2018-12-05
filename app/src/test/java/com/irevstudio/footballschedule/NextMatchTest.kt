package com.irevstudio.footballschedule

import com.google.gson.Gson
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.api.FootballDBApi
import com.irevstudio.footballschedule.model.Match
import com.irevstudio.footballschedule.model.MatchResponse
import com.irevstudio.footballschedule.ui.match.MatchPresenter
import com.irevstudio.footballschedule.ui.match.MatchView
import com.irevstudio.footballschedule.util.TestContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchTest{

    @Mock
    lateinit var view: MatchView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view,apiRepository,gson, TestContextProvider())
    }

    @Test
    fun getListMatch(){
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val leagueId = "4328"
        val eventType = "prev"

        GlobalScope.launch {
            `when` (gson.fromJson(apiRepository.
                    doRequest(FootballDBApi.ambilEvent(leagueId,eventType)).await(),
                    MatchResponse::class.java)).thenReturn(response)

            presenter.ambilListEvent(leagueId,eventType)

           verify(view).showLoading()
            verify(view).showEvent(match)
            verify(view).hideLoading()
        }
    }

}