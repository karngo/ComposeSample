package com.example.composesample.analytics

import android.util.Log

class LocalAnalytics : AppAnalytics {
    override fun logEvent(event: AppEvent) {
        Log.d("events_log", "logEvent: $event")
    }
}