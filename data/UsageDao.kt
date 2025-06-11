package com.zerosde.sentinel.data

import androidx.room.*
import com.zerosde.sentinel.models.AppUsageEntry

@Dao
interface UsageDao {
    @Query("SELECT * FROM usage_data WHERE date = :date")
    suspend fun getUsageForDate(date: String): List<AppUsageEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entry: AppUsageEntry)
}
