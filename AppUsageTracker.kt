package com.zerosde.sentinel

import android.app.usage.UsageStatsManager
import android.content.Context
import java.util.*

class AppUsageTracker(private val context: Context) {
    fun getTodayUsage(): Map<String, Long> {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val start = System.currentTimeMillis() - 1000 * 60 * 60 * 24
        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            start,
            System.currentTimeMillis()
        )
        return stats.associate { it.packageName to it.totalTimeInForeground }
    }
}