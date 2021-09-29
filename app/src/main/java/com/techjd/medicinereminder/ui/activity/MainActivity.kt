package com.techjd.medicinereminder.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.techjd.medicinereminder.background.MyReceiver
import com.techjd.medicinereminder.R
import com.techjd.medicinereminder.background.BootCompletedReceiver
import com.techjd.medicinereminder.data.timing.Timing
import com.techjd.medicinereminder.utils.MyUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var alarm: AlarmManager
    private lateinit var alarm1: AlarmManager
    private lateinit var alarm2: AlarmManager

    val medicineTimePref: String? = "medicinePrefs"

    /*
    B stands for Breakfast , L stands for Lunch and D stands for Dinner
    M stands for Morning , A stands for Afternoon and N stands for Night
    b stands for Before and a stands for After
    T stands for Time
     */
    val bMB = "bMB"
    val aMB = "aMB"
    val bAL = "bAL"
    val aAL = "aAL"
    val bND = "bND"
    val aND = "aND"
    val MBT = "MBT"
    val ALT = "ALT"
    val NDT = "NDT"

    lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val receiver = ComponentName(applicationContext, BootCompletedReceiver::class.java)

        applicationContext.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        val navcontroller = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.addFragment,
                R.id.settingsFragment,
                R.id.aboutFragment
            )
        )

        bottomNav.setupWithNavController(navcontroller)


//        sp = getSharedPreferences(medicineTimePref, Context.MODE_PRIVATE);
//
//        val editor = sp.edit()
//
//
//        val timingDao = MedicinesDatabase.getDatabase(application).timingDao()
//
        val calender = Calendar.getInstance()
//
//
//        var repository = TimeRepository(timingDao)
        val timing = Timing(0, "23", "37")
//        val time2 = Timing(0, "23", "26")
//
        calender.set(
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DATE),
            timing.hour.toInt(),
            timing.min.toInt()
        )
//
//
//        lifecycleScope.launch {
//            val job = async(Dispatchers.Main) {
//                repository.insert(timing)
//                setAlarm(time = calender.timeInMillis)
//                repository.insert(time2)
//            }
//            job.await()
//        }
//
//
//
        val pattern = "hh:mm a"
        val dateFormat: DateFormat = SimpleDateFormat(pattern)
        val disp: SimpleDateFormat = SimpleDateFormat("HH:mm")

        val today = Date()
        println(dateFormat.format(today))

        Log.d("TIME", "${calender.get(Calendar.HOUR)}:${calender.get(Calendar.MINUTE)}")

        val cT =
            MyUtils.convertInto24("${calender.get(Calendar.HOUR)}:${calender.get(Calendar.MINUTE)}")
        val date: Date =
            disp.parse("${calender.get(Calendar.HOUR)}:${calender.get(Calendar.MINUTE)}")
        Log.d(
            "CT",
            MyUtils.convertInto24(dateFormat.format(today))
        )
//        var time: String
//        time = MyUtils.convertInto24(dateFormat.format(today))

//        lifecycleScope.launch {
//            val timeList = repository.getAll()
//
//            for (i in 0..(timeList.size - 1)) {
//                Log.d("TIMELIST", "${timeList[i].hour} : ${timeList[i].min}")
//                if (timeList[i].equals(time)) {
//                    println(timeList[i])
//                    alarm = getSystemService(ALARM_SERVICE) as AlarmManager
//                    calender.set(
//                        calender.get(Calendar.YEAR),
//                        calender.get(Calendar.MONTH),
//                        calender.get(Calendar.DATE),
//                        timeList[i].hour.toInt(),
//                        timeList[i].min.toInt()
//                    )
//                    setAlarm(calender.timeInMillis)
//                }
//            }
//        }

    }


//    fun selectTime(view: View) {
//        val timePick = TimePickerFragment()
//        timePick.show(supportFragmentManager, "pickTime")
//    }
//
    private fun setAlarm(time: Long) {
        alarm = getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, MyReceiver::class.java)

        val pi = PendingIntent.getBroadcast(this, Math.random().toInt(), intent, 0)

        alarm.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pi
        )
    }

//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        TODO("Not yet implemented")
//    }

}