package com.example.composesample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.composesample.ui.navigation.AppNavGraph
import com.example.composesample.ui.theme.ComposeSampleTheme

@Composable
fun MainApp() {
    val navController = rememberNavController()

    ComposeSampleTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavGraph(navController, innerPadding)
        }
    }
}
