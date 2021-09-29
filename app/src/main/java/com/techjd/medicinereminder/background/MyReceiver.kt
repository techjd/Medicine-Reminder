package com.techjd.medicinereminder.background

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import com.techjd.medicinereminder.ui.activity.UIActivity


class MyReceiver : BroadcastReceiver() {
    @SuppressLint("InvalidWakeLockTag")
    override fun onReceive(context: Context, intent: Intent?) {
        val powerM = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wake = powerM.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "")
        wake.acquire(1*60*1000L)
        val inte = Intent(context, UIActivity::class.java)
        inte.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(inte)
        wake.release()
//        val notif1 = Notification()
//        for (i in 1..10) {
//            Thread.sleep(2500)
//            notif1.forNotification(
//                CHANNEL_ID = i.toString(),
//                notificationID = i,
//                context = context,
//                title = "Notification $i",
//                contentText = "Content For Notification $i"
//            )
//        }

    }
}