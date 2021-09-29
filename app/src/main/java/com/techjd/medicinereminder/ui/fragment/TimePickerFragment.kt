package com.techjd.medicinereminder.ui.fragment

//import android.app.Dialog
//import android.app.TimePickerDialog
//import android.app.TimePickerDialog.OnTimeSetListener
//import android.os.Bundle
//import android.widget.TimePicker
//import androidx.fragment.app.DialogFragment
//import java.util.*
//
//class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener{
//    private var listener: OnTimeSetListener? = null
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val c = Calendar.getInstance()
//        val hour = c.get(Calendar.HOUR_OF_DAY)
//        val minute = c.get(Calendar.MINUTE)
//        return TimePickerDialog(
//            activity,
//            this,
//            hour,
//            minute,
//            android.text.format.DateFormat.is24HourFormat(activity)
//        )
//    }
//
//
//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//
//    }
//}


import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import com.google.android.material.internal.ContextUtils.getActivity
import java.util.*

class TimePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]
        return TimePickerDialog(
            activity,
            activity as OnTimeSetListener?,
            hour,
            minute,
            DateFormat.is24HourFormat(activity)
        )
    }
}