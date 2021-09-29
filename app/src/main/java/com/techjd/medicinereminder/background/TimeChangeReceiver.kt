package com.techjd.medicinereminder.background

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.techjd.medicinereminder.data.medicines.Medicines
import com.techjd.medicinereminder.data.medicines.MedicinesDatabase
import com.techjd.medicinereminder.utils.SpecialAlarmManager
import com.techjd.medicinereminder.viewmodel.MedicinesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class TimeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val calender = Calendar.getInstance()
        if (intent?.action == "android.intent.action.TIME_SET") {
            val timingDao = MedicinesDatabase.getDatabase(context!!.applicationContext)
            var list: List<Medicines> = emptyList()
            GlobalScope.launch(Dispatchers.Main) {
                list = timingDao.medicinesDao().getM()
                for (i in 0..(list.size- 1)) {
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