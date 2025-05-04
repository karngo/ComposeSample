package com.example.composesample.ui.screen.insurancelist

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
class InsuranceHomeViewModel @Inject constructor(private val insuranceRepository: InsuranceRepository) :
    ViewModel() {

    @Inject
    lateinit var appAnalytics: AppAnalytics

    private val _insurances =
        MutableStateFlow<UIState<List<Insurance>>>(UIState.Valid(emptyList()))
    val insurances: StateFlow<UIState<List<Insurance>>> = _insurances

    fun fetchInsurances() {
        viewModelScope.launch(Dispatchers.IO) {
            _insurances.value = UIState.Loading
            _insurances.value = insuranceRepository.getAllInsurances().let { UIState.Valid(it) }
        }
    }

    fun logEvent(event: AppEvent) {
        appAnalytics.logEvent(event)
    }
}