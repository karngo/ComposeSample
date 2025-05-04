package com.example.composesample.data

import com.example.composesample.data.model.Insurance
import javax.inject.Inject

class InsuranceRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    InsuranceRepository {
    override suspend fun getAllInsurances(): List<Insurance> {
        return apiService.fetchInsurances().body() ?: emptyList()
    }

    override suspend fun getInsuranceDetails(id: String): Insurance? {
        return apiService.fetchInsuranceDetails(id).body()
    }
}