package com.irevstudio.footballschedule.ui.team.teamdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.irevstudio.footballschedule.ui.team.teamdetail.TeamDetailActivity.Companion.dataTeamDesc
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.nestedScrollView

class Overview: Fragment(){

    private lateinit var teamDesc: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val ui= UI{

            nestedScrollView {
                lparams(width = matchParent, height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)



                teamDesc = textView {
                    textSize = 14f
                }
            }


        }

        teamDesc.text = dataTeamDesc

        return ui.view
    }
}