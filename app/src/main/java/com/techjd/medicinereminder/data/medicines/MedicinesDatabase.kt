package com.techjd.medicinereminder.data.medicines

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techjd.medicinereminder.data.timing.Timing
import com.techjd.medicinereminder.data.timing.TimingDao


@Database(entities = [Medicines::class, Timing::class], version = 1)
abstract class MedicinesDatabase : RoomDatabase() {

    abstract fun medicinesDao(): MedicinesDao
    abstract fun timingDao(): TimingDao

    companion object {
        @Volatile
        private var INSTANCE: MedicinesDatabase? = null

        fun getDatabase(context: Context): MedicinesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicinesDatabase::class.java,
                    "medicines_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}