package com.irevstudio.footballschedule.ui.team

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.irevstudio.footballschedule.R
import org.jetbrains.anko.*

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.logo_tim
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.nama_tim
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }

            }
        }

    }
}

class PlayerListUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.playerPict
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.playerName
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }

            }
        }

    }
}