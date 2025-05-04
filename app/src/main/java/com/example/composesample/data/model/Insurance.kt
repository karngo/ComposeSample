package com.example.composesample.data.model

import com.google.gson.annotations.SerializedName

data class Insurance(
    val title: String,
    val description: String,

    @SerializedName("monthly_premium")
    val monthlyPremium: Int,

    val type: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("covrage")
    val coverage: String,

    val tenure: String,
    val id: String
)
