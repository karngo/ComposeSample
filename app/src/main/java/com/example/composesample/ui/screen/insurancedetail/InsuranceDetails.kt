package com.example.composesample.ui.screen.insurancedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.composesample.R
import com.example.composesample.analytics.AppEvent
import com.example.composesample.data.model.Insurance
import com.example.composesample.model.UIState
import com.example.composesample.ui.theme.ComposeSampleTheme

@Composable
fun InsuranceDetails(id: String, viewmodel: InsuranceDetailsViewModel = hiltViewModel()) {
    val insuranceDetail = viewmodel.insuranceDetail.collectAsStateWithLifecycle()
    var isDialogOpen by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewmodel.logEvent(
            AppEvent("InsuranceDetailViewed").apply {
                properties["insurance_code"] = id
            }
        )
        viewmodel.fetchInsurances(id)
    }

    when (val state = insuranceDetail.value) {
        is UIState.Valid -> {
            state.data?.let {
                InsuranceDetail(it) {
                    viewmodel.logEvent(
                        AppEvent("buy_now_clicked").apply {
                            properties["insurance_code"] = it.id
                        }
                    )
                    isDialogOpen = true
                }
            }
        }

        is UIState.Loading -> {
            Text("Loading...")
        }
    }

    if (isDialogOpen) {
        AlertDialog(
            title = {
                Text(text = stringResource(R.string.purchase_successful))
            },
            text = {
                Text(text = stringResource(R.string.you_have_successfully_purchased_the_insurance))
            },
            onDismissRequest = {
                isDialogOpen = false
            },
            confirmButton = {
                TextButton(onClick = { isDialogOpen = false }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}

@Composable
fun InsuranceDetail(insurance: Insurance, onBuyClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

        Button(onClick = onBuyClick) {
            Text(stringResource(R.string.buy_now))
        }
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
            ) {}
        }
    }
}