package com.zerosde.sentinel.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usage_data", primaryKeys = ["packageName", "date"])
data class AppUsageEntry(
    val packageName: String,
    val date: String,
    val timeUsedMillis: Long
)
