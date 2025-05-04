package com.example.composesample.ui.screen.insurancedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesample.analytics.AppAnalytics
import com.example.composesample.analytics.AppEvent
import com.example.composesample.data.InsuranceRepository
import com.example.composesample.data.model.Insurance
import com.example.composesample.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsuranceDetailsViewModel @Inject constructor(private val insuranceRepository: InsuranceRepository) :
    ViewModel() {

    @Inject
    lateinit var appAnalytics: AppAnalytics

    private val _insuranceDetail = MutableStateFlow<UIState<Insurance?>>(UIState.Valid(null))
    val insuranceDetail: StateFlow<UIState<Insurance?>> = _insuranceDetail

    fun fetchInsurances(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _insuranceDetail.value = UIState.Loading
            _insuranceDetail.value = insuranceRepository.getInsuranceDetails(id).let {
                UIState.Valid(it)
            }
        }
    }

    fun logEvent(event: AppEvent) {
        appAnalytics.logEvent(event)
    }
}