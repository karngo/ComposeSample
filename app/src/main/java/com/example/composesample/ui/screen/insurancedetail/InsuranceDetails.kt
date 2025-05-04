package com.example.composesample.ui.screen.insurancedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.composesample.data.model.Insurance
import com.example.composesample.ui.theme.ComposeSampleTheme

@Composable
fun InsuranceDetails(id: String, viewmodel: InsuranceDetailsViewModel = hiltViewModel()) {
    val insuranceDetail = viewmodel.insuranceDetail.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewmodel.fetchInsurances(id)
    }

    insuranceDetail.value?.let {
        InsuranceDetail(it)
    }
}

@Composable
fun InsuranceDetail(insurance: Insurance) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(insurance.type)
            Text(insurance.title)
            Text(insurance.description)
            Text(insurance.monthlyPremium.toString())
        }
        AsyncImage(model = insurance.imageUrl, contentDescription = "Insurance Image")
    }
}

@Composable
@Preview(showBackground = true)
fun InsuranceDetailPreview() {
    ComposeSampleTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            InsuranceDetail(
                Insurance(
                    title = "Health Protect Plus",
                    description = "Comprehensive health coverage including hospitalization, diagnostics, and surgery.",
                    monthlyPremium = 950,
                    type = "Health Insurance",
                    imageUrl = "https://picsum.photos/id/1/200",
                    coverage = "₹10,00,000",
                    tenure = "1 Year",
                    id = "1"
                )
            )
        }
    }
}