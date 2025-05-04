package com.example.composesample.di

import android.content.Context
import com.example.composesample.analytics.AppAnalytics
import com.example.composesample.analytics.LocalAnalytics
import com.example.composesample.data.ApiService
import com.example.composesample.data.InsuranceRepository
import com.example.composesample.data.InsuranceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://6812fd78129f6313e20ffd18.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideInsuranceRepository(apiService: ApiService): InsuranceRepository {
        return InsuranceRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideAppAnalytics(@ApplicationContext appContext: Context): AppAnalytics {
        return LocalAnalytics(appContext)
    }
}