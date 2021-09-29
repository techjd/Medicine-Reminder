package com.techjd.medicinereminder.ui.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.techjd.medicinereminder.background.MyReceiver
import com.techjd.medicinereminder.data.medicines.Medicines
import com.techjd.medicinereminder.databinding.AddFragmentBinding
import com.techjd.medicinereminder.utils.MyUtils
import com.techjd.medicinereminder.viewmodel.MedicinesViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {
    private var _binding: AddFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var alarm: AlarmManager
    private lateinit var medicinesViewModel: MedicinesViewModel

    companion object {
        fun newInstance() = AddFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        medicinesViewModel =
            ViewModelProvider(this).get(MedicinesViewModel(activity!!.application)::class.java)


        val calender = Calendar.getInstance()
        val pattern = "hh:mm a"
        val dateFormat: DateFormat = SimpleDateFormat(pattern)
        val today = Date()
        Log.d(
            "CT",
            MyUtils.convertInto24(dateFormat.format(today))
        )

        binding.openDialog.setOnClickListener {
            openDialog()
        }

        binding.addMedicine.setOnClickListener {

            val medicine = Medicines(
                title = binding.editTextMedicineName.text.trim().toString(),
                Description = binding.editTextDescription.text.trim().toString(),
                hour = binding.hour.text.trim().toString(),
                min = binding.minute.text.trim().toString()
            )

            calender.set(
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DATE),
                binding.hour.text.trim().toString().toInt(),
                binding.minute.text.trim().toString().toInt()
            )


            Log.d("TIME", "TIME IS ${calender.time.hours} : ${calender.time.minutes}")

            lifecycleScope.launch {
                medicinesViewModel.addMedicines(
                    medicines = medicine
                )
                setAlarm(calender.timeInMillis)
            }
            binding.apply {
                editTextMedicineName.setText("")
                editTextDescription.setText("")
                hour.setText("")
                minute.setText("")
            }
            Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
        }
    }

    private fun openDialog() {
        val isSystem24 = is24HourFormat(requireContext())
        val clockFormat = if (isSystem24) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Choose Time")
            .build()
        picker.show(childFragmentManager, "PICKTIME")

        picker.addOnPositiveButtonClickListener {
            binding.selectedTime.text = "${picker.hour}:${picker.minute}"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAlarm(time: Long) {
        alarm = context?.getSystemService(ALARM_SERVICE) as AlarmManager

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