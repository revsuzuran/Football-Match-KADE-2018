package com.irevstudio.footballschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.irevstudio.footballschedule.ui.player.PlayerDetailInfo
import com.irevstudio.footballschedule.ui.player.PlayerOverview

class PlayerDetailTabAdapter (fm: FragmentManager?) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> PlayerOverview()
            else -> PlayerDetailInfo()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Overview"
            else -> "Player Info"
        }
    }

}