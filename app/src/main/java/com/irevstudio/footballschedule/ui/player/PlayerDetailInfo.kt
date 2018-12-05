package com.irevstudio.footballschedule.ui.player

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.irevstudio.footballschedule.ui.player.PlayerDetailActivity.Companion.dataPlayerDetailHeight
import com.irevstudio.footballschedule.ui.player.PlayerDetailActivity.Companion.dataPlayerDetailWeight
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI

class PlayerDetailInfo : Fragment(){

    private lateinit var playerDetailHeight: TextView
    private lateinit var playerDetailWeight: TextView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val ui = UI{

            verticalLayout {
                lparams(width = matchParent,  height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    verticalLayout {
                        lparams { weight = 0.5f }
                        gravity = Gravity.CENTER

                        textView {
                            textSize = 20f
                            text = "HEIGHT"
                        }
                        playerDetailHeight = textView {
                            textSize = 50f
                            textColor = Color.parseColor("#3498db")
                        }
                    }

                    verticalLayout {
                        lparams { weight = 0.5f }
                        gravity = Gravity.CENTER

                        textView {
                            textSize = 20f
                            text = "WEIGHT"
                        }
                        playerDetailWeight = textView {
                            textSize = 50f
                            textColor = Color.parseColor("#3498db")
                        }
                    }

                }

            }

        }

        playerDetailHeight.text = dataPlayerDetailHeight
        playerDetailWeight.text = dataPlayerDetailWeight

        return ui.view
    }

}