package com.zerosde.sentinel.data

import androidx.room.*
import com.zerosde.sentinel.models.BlockedApp

@Dao
interface SentinelDao {
    @Query("SELECT * FROM blocked_apps")
    suspend fun getAll(): List<BlockedApp>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(app: BlockedApp)
}
