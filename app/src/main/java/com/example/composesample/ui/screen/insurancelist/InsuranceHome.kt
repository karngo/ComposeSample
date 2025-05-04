package com.example.composesample.ui.screen.insurancelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
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
import com.example.composesample.analytics.AppEvent
import com.example.composesample.data.model.Insurance
import com.example.composesample.ui.theme.ComposeSampleTheme

@Composable
fun InsuranceHome(
    viewmodel: InsuranceHomeViewModel = hiltViewModel(),
    onShowDetail: (insuranceId: String) -> Unit
) {
    val insuranceList = viewmodel.insurances.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewmodel.logEvent(AppEvent("InsuranceHomeViewed"))
        viewmodel.fetchInsurances()
    }

    Surface(modifier = Modifier.padding(12.dp)) {
        InsuranceList(insuranceList.value, onShowDetail)
    }
}

@Composable
fun InsuranceList(insurances: List<Insurance>, onItemClick: (id: String) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(insurances) { insurance ->
            Card(modifier = Modifier.fillMaxWidth(), onClick = { onItemClick(insurance.id) }) {
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
        }
    }
}


@Composable
@Preview(showBackground = true)
fun InsuranceListPreview() {
    ComposeSampleTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            InsuranceList(
                arrayListOf(
                    Insurance(
                        title = "Health Protect Plus",
                        description = "Comprehensive health coverage including hospitalization, diagnostics, and surgery.",
                        monthlyPremium = 950,
                        type = "Health Insurance",
                        imageUrl = "https://picsum.photos/id/1/200",
                        coverage = "₹10,00,000",
                        tenure = "1 Year",
                        id = "1"
                    ),
                    Insurance(
                        title = "Health Protect Plus",
                        description = "Comprehensive health coverage including hospitalization, diagnostics, and surgery.",
                        monthlyPremium = 950,
                        type = "Health Insurance",
                        imageUrl = "https://picsum.photos/id/1/200",
                        coverage = "₹10,00,000",
                        tenure = "1 Year",
                        id = "2"
                    )
                )
            ) {}
        }
    }
}