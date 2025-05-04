package com.example.composesample.analytics

interface AppAnalytics {
    fun logEvent(event: AppEvent)
}