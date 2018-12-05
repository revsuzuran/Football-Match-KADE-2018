package com.irevstudio.footballschedule.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.adapter.FavoriteTabAdapter

class FavoritesFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = FavoriteTabAdapter(activity?.supportFragmentManager)

        return view
    }
}