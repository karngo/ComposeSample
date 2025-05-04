package com.example.composesample.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.composesample.ui.screen.insurancedetail.InsuranceDetails
import com.example.composesample.ui.screen.insurancelist.InsuranceHome

@Composable
fun AppNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Destination.Insurances,
    ) {
        composable<Destination.Insurances> {
            InsuranceHome(onShowDetail = { id ->
                navController.navigate(Destination.InsuranceDetails(id))
            })
        }
        composable<Destination.InsuranceDetails> { navBackStackEntry ->
            val insuranceId = navBackStackEntry.toRoute<Destination.InsuranceDetails>().id
            InsuranceDetails(insuranceId)
        }
    }
}
