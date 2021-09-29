package com.techjd.medicinereminder.data.medicines

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicinesDao {

    @Query("SELECT * FROM medicines")
    fun getMedicines(): LiveData<List<Medicines>>

    @Query("SELECT * FROM medicines")
    suspend fun getM(): List<Medicines>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicine: Medicines)

    @Update
    suspend fun update(medicine: Medicines)

    @Delete
    suspend fun delete(medicine: Medicines)
}