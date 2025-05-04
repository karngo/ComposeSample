package com.example.composesample.analytics

data class AppEvent(
    val name: String,
    val properties: HashMap<String, String> = hashMapOf()
)
