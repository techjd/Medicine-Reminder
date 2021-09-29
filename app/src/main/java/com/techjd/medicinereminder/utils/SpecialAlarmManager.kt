package com.techjd.medicinereminder.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.techjd.medicinereminder.background.MyReceiver
import java.util.*

object SpecialAlarmManager {
    private lateinit var alarm: AlarmManager

    fun ring(context: Context, time: Long) {

        alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, MyReceiver::class.java)

        val pi = PendingIntent.getBroadcast(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        alarm.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            time,
            AlarmManager.INTERVAL_DAY,
            pi
        )

    }
}