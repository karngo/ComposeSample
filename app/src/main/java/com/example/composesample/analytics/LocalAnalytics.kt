package com.example.composesample.analytics

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalAnalytics(private val context: Context) : AppAnalytics {
    private val events: Array<String> = Array(EVENT_BATCH_SIZE) { "" }
    private var eventsCount = 0

    override fun logEvent(event: AppEvent) {
        if (eventsCount == EVENT_BATCH_SIZE - 1) {
            saveLogs()
            eventsCount = 0
        }

        events[eventsCount++] = Json.encodeToString(event)
    }

    private fun saveLogs() {
        val workRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<LogWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(workDataOf(LogWorker.LOG_KEY to events))
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork("Log_Worker", ExistingWorkPolicy.APPEND, workRequest)
    }

    companion object {
        const val EVENT_BATCH_SIZE = 20
    }
}