package com.irevstudio.footballschedule

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.irevstudio.footballschedule.R.id.*
import com.irevstudio.footballschedule.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)
    private val wait: Long = 3000


    @Test
    fun addAndRemoveFavoritesMatch(){

        //menunggu data terload
        Thread.sleep(wait)

        //memastikan recycleView pada LastMatch.kt sudah tampil
        onView(withId(list_event_lastMatch))
                .check(matches(isDisplayed()))

        //melakukan scroll sampai item ke 10
        onView(withId(list_event_lastMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        //melakukan klik pada item ke 10
        onView(withId(list_event_lastMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        //menunggu data terload pada MatchDetail.kt
        Thread.sleep(wait)

        //test scrolling ke atas , untuk melihat seluruh content *jika view terpotong
        onView(withId(swipe))
                .perform(swipeUp())

        //test scrolling bawah atau melakukan refresh pada view
        onView(withId(swipe))
                .perform(swipeDown())

        //menambahkan ke favorites
        onView(withId(add_to_favorite))
                .perform(click())

        //press back button
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

        //press favorites button
        onView(withId(favorites))
                .perform(click())

        //swipe ke kanan untuk melihat favorites match
        onView(withId(viewPager))
                .perform(swipeLeft())

        //click item pada posisi pertama
        if(getItemCount() > 0) {
            onView(withId(list_event_favorites))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        }

    }

    private fun getItemCount(): Int {
        val recyclerView = activityRule.activity.findViewById(R.id.list_event_favorites) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}