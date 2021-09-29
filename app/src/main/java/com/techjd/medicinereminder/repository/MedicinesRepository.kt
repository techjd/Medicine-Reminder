package com.techjd.medicinereminder.repository

import androidx.lifecycle.LiveData
import com.techjd.medicinereminder.data.medicines.Medicines
import com.techjd.medicinereminder.data.medicines.MedicinesDao

class MedicinesRepository(private val medicinesDao: MedicinesDao) {

    val getAllMedicines: LiveData<List<Medicines>> = medicinesDao.getMedicines()

    suspend fun getM(): List<Medicines> {
        return medicinesDao.getM()
    }

    suspend fun addMedicine(medicines: Medicines) {
        medicinesDao.insert(medicines)
    }

    suspend fun updateMedicine(medicines: Medicines) {
        medicinesDao.update(medicines)
    }

    suspend fun deleteMedicine(medicines: Medicines) {
        medicinesDao.delete(medicines)
    }
}