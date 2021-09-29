package com.techjd.medicinereminder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techjd.medicinereminder.data.medicines.Medicines
import com.techjd.medicinereminder.data.medicines.MedicinesDatabase
import com.techjd.medicinereminder.repository.MedicinesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicinesViewModel(application: Application) : AndroidViewModel(application) {
    val getAllMedicines: LiveData<List<Medicines>>
    private val repository: MedicinesRepository

    init {
        val medicinesDao = MedicinesDatabase.getDatabase(application).medicinesDao()
        repository = MedicinesRepository(medicinesDao)
        getAllMedicines = repository.getAllMedicines
    }

    suspend fun addMedicines(medicines: Medicines) {

        repository.addMedicine(medicines)

    }

    suspend fun updateMedicines(medicines: Medicines) {

        repository.updateMedicine(medicines)

    }

    suspend fun deleteMedicines(medicines: Medicines) {

        repository.deleteMedicine(medicines)

    }
}