package com.example.composesample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.composesample.model.AppLanguage
import com.example.composesample.ui.component.LanguageDropdown
import com.example.composesample.ui.navigation.AppNavGraph
import com.example.composesample.ui.theme.ComposeSampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(onChangeLanguage: (language: AppLanguage) -> Unit) {
    val navController = rememberNavController()

    ComposeSampleTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                    actions = {
                        LanguageDropdown { onChangeLanguage(it) }
                    },
                )
            },
        ) { innerPadding ->
            AppNavGraph(navController, innerPadding)
        }
    }
}
