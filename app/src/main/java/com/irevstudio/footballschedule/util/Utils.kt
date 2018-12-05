/*
 * Created On : 10/25/18 10:03 PM
 * Author : Aqil Prakoso
 * Copyright (c) 2018 iRevStudio
 *
 */

package com.irevstudio.footballschedule.util

import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.inVisible(){
    visibility = View.GONE
}

object DateUtils {

    fun convert(date : String): String {
        try {
            val baseDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val newDate =  SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
            val gmtTime: TimeZone = TimeZone.getTimeZone("GMT+07")
            newDate.timeZone = gmtTime
            return newDate.format(baseDate.parse(date))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}

object TimeUtils {

    fun convert(time : String): String {
        try {
            val baseTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val newTime =  SimpleDateFormat("KK:mm a", Locale.getDefault())
            val gmtTime: TimeZone = TimeZone.getTimeZone("GMT+07")
            newTime.timeZone = gmtTime
            return newTime.format(baseTime.parse(time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}