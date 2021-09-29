package com.techjd.medicinereminder.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
object MyUtils {
    fun convertInto24(time: String): String {
        try {
            val disp: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val parse: SimpleDateFormat = SimpleDateFormat("hh:mm a")
            val date: Date = parse.parse(time)
            return disp.format(date)
        } catch (e: Exception) {
            return e.toString()
        }

    }

    fun convertInto12(time: String): String {
        try {
            val displayFormat = SimpleDateFormat("HH:mm")
            val parseFormat = SimpleDateFormat("hh:mm a")
            val date1 = displayFormat.parse(time)
            return parseFormat.format(date1)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun add30Minutes(hour: Int, minute: Int, amount: Int = 20): String {
        val displayFormat = SimpleDateFormat("HH:mm")
        val calender = Calendar.getInstance()
        calender.set(
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DATE),
            hour,
            minute
        )
        calender.add(Calendar.MINUTE, amount)
        val time: String = "${calender.time.hours}:${calender.time.minutes}"
        val date: Date = displayFormat.parse(time)
        return displayFormat.format(date)
    }

    fun minus20Minutes(hour: Int, minute: Int, amount: Int = -20): String {
        val displayFormat = SimpleDateFormat("HH:mm")
        val calender = Calendar.getInstance()
        calender.set(
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DATE),
            hour,
            minute
        )
        calender.add(Calendar.MINUTE, amount)
        val time: String = "${calender.time.hours}:${calender.time.minutes}"
        val date: Date = displayFormat.parse(time)
        return displayFormat.format(date)
    }
}