package com.techjd.medicinereminder.data.timing

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timing")
class Timing(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "hour")
    val hour: String,
    @ColumnInfo(name = "minute")
    val min: String
)