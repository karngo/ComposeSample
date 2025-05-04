package com.example.composesample.ui.screen.insurancelist

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
class InsuranceHomeViewModel @Inject constructor(private val insuranceRepository: InsuranceRepository) :
    ViewModel() {

    private val _insurances = MutableStateFlow<List<Insurance>>(emptyList())
    val insurances: StateFlow<List<Insurance>> = _insurances

    fun fetchInsurances() {
        viewModelScope.launch(Dispatchers.IO) {
            _insurances.value = insuranceRepository.getAllInsurances()
        }
    }
}