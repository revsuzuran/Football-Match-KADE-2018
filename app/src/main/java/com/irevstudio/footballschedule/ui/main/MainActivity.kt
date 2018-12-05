/*
 * Created On : 10/25/18 10:03 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.R.id.*
import com.irevstudio.footballschedule.ui.FavoritesFragment
import com.irevstudio.footballschedule.ui.match.MatchFragment
import com.irevstudio.footballschedule.ui.match.MatchSearchFragment
import com.irevstudio.footballschedule.ui.team.TeamFragment
import com.irevstudio.footballschedule.ui.team.TeamSearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var querySearch: String? = null
    private var fragmentCheck: String? = null

    companion object {
        lateinit var dataSearch : String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->

            var fragment: Fragment? = null
            when (item.itemId) {
                teams -> {
                   fragment = TeamFragment()
                    fragmentCheck = getString(R.string.TEAM_FRAGMENT)
                }
                events -> {
                    fragment = MatchFragment()
                    fragmentCheck = getString(R.string.MATCH_FRAGMENT)
                }
                favorites -> {
                    fragment = FavoritesFragment()
                    fragmentCheck = null
                }

            }
            loadFragment(fragment)
            return@setOnNavigationItemSelectedListener true
        }
        bottom_navigation.selectedItemId = events

    }

    private fun loadFragment(fragment: Fragment?){
        if (fragment != null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frameLayout, fragment)
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search.. "
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != ""){
                    dataSearch = query.toString()
                    checkFragment()}
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun checkFragment(){

        when {
            fragmentCheck.equals(getString(R.string.TEAM_FRAGMENT)) -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frameLayout, TeamSearchFragment())
                        .commit()

            }
            fragmentCheck.equals(getString(R.string.MATCH_FRAGMENT)) -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frameLayout, MatchSearchFragment())
                        .commit()
            }
            else -> {  }
        }

    }

}

