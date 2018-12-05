package com.irevstudio.footballschedule.ui.player

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.adapter.PlayerDetailTabAdapter
import com.irevstudio.footballschedule.api.ApiRepository
import com.irevstudio.footballschedule.model.Player
import com.irevstudio.footballschedule.ui.team.teamdetail.PlayerPresenter
import com.irevstudio.footballschedule.ui.team.teamdetail.PlayerView
import com.irevstudio.footballschedule.util.inVisible
import com.irevstudio.footballschedule.util.visible
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh

class PlayerDetailActivity : AppCompatActivity(), PlayerView {

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var playerPict: ImageView
    private lateinit var playerName: TextView
    private lateinit var playerNationality: TextView
    private lateinit var viewPager: ViewPager
    private lateinit var presenter: PlayerPresenter
    private lateinit var id: String

    companion object {
        var dataPlayerDesc: String? = null
        var dataPlayerDetailHeight: String? = null
        var dataPlayerDetailWeight: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.title = "Player Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        id = intent.getStringExtra("id").toString()
        Log.d("idIntent",id)

        playerPict = find(R.id.playerDetailPict)
        playerName = find(R.id.playerDetailName)
        playerNationality = find(R.id.playerDetailNationality)
        viewPager = find(R.id.playerDetailViewPager)
        swipeRefresh = find(R.id.swipePlayerDetail)
        progressBar = find(R.id.pgBar_playerDetail)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, apiRepository, gson )
        presenter.getDetailPlayer(id)

        swipeRefresh.onRefresh {
            presenter.getDetailPlayer(id)
        }
    }

    override fun showLoading() {
        progressBar.visible()

    }

    override fun hideLoading() {
        progressBar.inVisible()
    }


    override fun show(data: List<Player>) {
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].strCutout).into(playerPict)
        playerName.text = data[0].strPlayer
        playerNationality.text = data[0].strNationality

        dataPlayerDesc  = data[0].strDescriptionEN
        dataPlayerDetailHeight = data[0].strHeight
        dataPlayerDetailWeight = data[0].strWeight

        viewPager.adapter = PlayerDetailTabAdapter(this.supportFragmentManager)
    }

}
