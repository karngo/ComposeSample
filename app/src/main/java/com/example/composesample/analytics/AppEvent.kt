package com.example.composesample.analytics

import kotlinx.serialization.Serializable

@Serializable
data class AppEvent(
    val name: String,
    val properties: HashMap<String, String> = hashMapOf()
)
