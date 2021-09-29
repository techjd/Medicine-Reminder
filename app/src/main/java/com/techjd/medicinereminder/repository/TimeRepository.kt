package com.techjd.medicinereminder.repository

import com.techjd.medicinereminder.data.timing.Timing
import com.techjd.medicinereminder.data.timing.TimingDao

class TimeRepository(private val timingDao: TimingDao) {

    suspend fun getAll() = timingDao.getTime()

    suspend fun insert(timing: Timing) {
        timingDao.insert(timing)
    }
}