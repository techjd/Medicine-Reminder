package com.techjd.medicinereminder.data.medicines

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medicines")
data class Medicines(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var Description: String,
    @ColumnInfo(name = "hour")
    val hour: String,
    @ColumnInfo(name = "minute")
    val min: String
)


//@ColumnInfo(name = "bMB")
//var bMB: Boolean,
//@ColumnInfo(name = "aMB")
//var aMB: Boolean,
//@ColumnInfo(name = "bAL")
//var bAL: Boolean,
//@ColumnInfo(name = "aAL")
//var aAL: Boolean,
//@ColumnInfo(name = "bND")
//var bND: Boolean,
//@ColumnInfo(name = "aND")
//var aND: Boolean