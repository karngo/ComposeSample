package com.example.composesample.ui.screen.insurancedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesample.data.InsuranceRepository
import com.example.composesample.data.model.Insurance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsuranceDetailsViewModel @Inject constructor(private val insuranceRepository: InsuranceRepository) :
    ViewModel() {

    private val _insuranceDetail = MutableStateFlow<Insurance?>(null)
    val insuranceDetail: StateFlow<Insurance?> = _insuranceDetail

    fun fetchInsurances(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _insuranceDetail.value = insuranceRepository.getInsuranceDetails(id)
        }
    }
}