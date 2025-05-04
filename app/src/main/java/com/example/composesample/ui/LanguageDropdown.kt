package com.example.composesample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composesample.model.AppLanguage

@Composable
fun LanguageDropdown(onChange: (language: AppLanguage) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { isExpanded = !isExpanded }) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Change Language",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            for (appLanguage in AppLanguage.entries) {
                DropdownMenuItem(
                    text = { Text(appLanguage.name) },
                    onClick = {
                        onChange(appLanguage)
                        isExpanded = false
                    },
                )
            }
        }
    }
}