package com.example.composesample.ui.navigation

import kotlinx.serialization.Serializable

object Destination {
    @Serializable
    data object Insurances

    @Serializable
    data class InsuranceDetails(val id: String)
}