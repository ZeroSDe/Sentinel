package com.zerosde.sentinel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SentinelViewModel(application: Application) : AndroidViewModel(application) {
    val blockedApps = MutableLiveData<List<BlockedApp>>()
    val usageStats = MutableLiveData<Map<String, Long>>()

    fun loadBlockedApps() {
        viewModelScope.launch {
            blockedApps.value = SentinelDatabase.getDatabase(getApplication()).dao().getAll()
        }
    }

    fun addBlockedApp(packageName: String) {
        viewModelScope.launch {
            SentinelDatabase.getDatabase(getApplication()).dao().insert(BlockedApp(packageName))
            loadBlockedApps()
        }
    }

    fun fetchUsageStats() {
        val tracker = AppUsageTracker(getApplication())
        usageStats.value = tracker.getTodayUsage()
    }
}
