package com.zerosde.sentinel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zerosde.sentinel.models.BlockedApp
import com.zerosde.sentinel.models.AppUsageEntry

@Database(entities = [BlockedApp::class, AppUsageEntry::class], version = 1)
abstract class SentinelDatabase : RoomDatabase() {
    abstract fun dao(): SentinelDao
    abstract fun usageDao(): UsageDao

    companion object {
        @Volatile private var INSTANCE: SentinelDatabase? = null

        fun getDatabase(context: Context): SentinelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SentinelDatabase::class.java,
                    "sentinel_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}