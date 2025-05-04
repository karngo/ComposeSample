package com.example.composesample.data

import com.example.composesample.data.model.Insurance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("InsuranceProduct")
    suspend fun fetchInsurances(): Response<List<Insurance>>

    @GET("InsuranceProduct/{id}")
    suspend fun fetchInsuranceDetails(@Path("id") id: String): Response<Insurance>
}