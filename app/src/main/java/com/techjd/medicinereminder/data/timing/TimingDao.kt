package com.techjd.medicinereminder.data.timing

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimingDao {

    @Query("SELECT * FROM timing")
    suspend fun getTime(): List<Timing>

    @Insert
    suspend fun insert(timing: Timing)
}