/*
 * Created On : 10/30/18 2:13 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.ui.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.irevstudio.footballschedule.R
import com.irevstudio.footballschedule.adapter.MatchTabAdapter
import com.irevstudio.footballschedule.ui.match.lastmatch.LastMatch.Companion.presenterPrev
import com.irevstudio.footballschedule.ui.match.nextmatch.NextMatch.Companion.presenterNext
import com.jaredrummler.materialspinner.MaterialSpinner
import org.jetbrains.anko.find


class MatchFragment : Fragment(){

    private lateinit var viewPager: ViewPager
    private lateinit var spinner: MaterialSpinner
    lateinit var ligaId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)


        spinner = view.find(R.id.matchSpinner)
        viewPager = view.findViewById(R.id.viewPager)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.setAdapter(spinnerAdapter)
        spinner.setOnItemSelectedListener { _, position, _, _ ->
            ligaId = resources.getStringArray(R.array.leagueId)[position]
            presenterNext.ambilListEvent(ligaId,"next")
            presenterPrev.ambilListEvent(ligaId, "prev") }

        viewPager.adapter = MatchTabAdapter(activity?.supportFragmentManager)

        return view
    }

}
