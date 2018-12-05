package com.irevstudio.footballschedule.ui.match

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.irevstudio.footballschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

                cardView {
                    lparams{width = matchParent
                            margin = dip(5)}

                    verticalLayout {

                        textView{
                            id = R.id.nextMatch_date
                            textSize = 14f
                            textColor  = Color.parseColor("#3498db")
                            textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                        }.lparams{width = matchParent
                            height = wrapContent
                            margin = dip(2)}

                        textView {
                            id = R.id.nextMatch_time
                            textSize = 14f
                            textColor = Color.parseColor("#e74c3c")
                            gravity = Gravity.CENTER
                        }


                        linearLayout {
                            padding = dip(5)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER

                            textView {
                                id = R.id.nextMatch_homeName
                                textSize = 16f
                                textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                weight = 0.5f
                            }


                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER

                                textView {
                                    id = R.id.nextMatch_homeSkor
                                    textSize = 16f
                                    textColor = Color.parseColor("#3498db")
                                }

                                textView {
                                    text = ctx.getString(R.string.versus)
                                    textSize = 12f
                                }.lparams { margin = dip(5) }

                                textView {
                                    id = R.id.nextMatch_awaySkor
                                    textSize = 16f
                                    textColor = Color.parseColor("#3498db")
                                }
                            }

                            textView {
                                id = R.id.nextMatch_awayName
                                textSize = 16f
                                textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                weight = 0.5f
                            }

                        }

                    }
                }
        }
    }
}