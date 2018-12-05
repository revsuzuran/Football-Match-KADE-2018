package com.irevstudio.footballschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.irevstudio.footballschedule.ui.favorites.MatchFavoritesFragment
import com.irevstudio.footballschedule.ui.favorites.TeamFavoritesFragment

class FavoriteTabAdapter (fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TeamFavoritesFragment()
            else -> MatchFavoritesFragment()
        }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Teams"
            else -> "Matchs"
        }
    }
}