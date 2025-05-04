package com.example.composesample.data

import com.example.composesample.data.model.Insurance

interface InsuranceRepository {
    suspend fun getAllInsurances(): List<Insurance>

    suspend fun getInsuranceDetails(id: String): Insurance
}