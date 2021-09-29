package com.techjd.medicinereminder.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.techjd.medicinereminder.data.medicines.Medicines
import com.techjd.medicinereminder.data.medicines.MedicinesDatabase
import com.techjd.medicinereminder.utils.SpecialAlarmManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val calender = Calendar.getInstance()

        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {

            val timingDao = MedicinesDatabase.getDatabase(context!!.applicationContext)
            val timeList = timingDao.medicinesDao().getMedicines()
            var list: List<Medicines> = emptyList()
            GlobalScope.launch(Dispatchers.Main) {
                list = timeList.value!!
                for (i in 0..(list.size - 1)) {
                    calender.set(
                        calender.get(Calendar.YEAR),
                        calender.get(Calendar.MONTH),
                        calender.get(Calendar.DATE),
                        list[i].hour.toInt(),
                        list[i].min.toInt()
                    )
                    if (System.currentTimeMillis() < calender.timeInMillis) {
                        SpecialAlarmManager.ring(context, calender.timeInMillis)
                    }

                }
            }
        }
    }
}