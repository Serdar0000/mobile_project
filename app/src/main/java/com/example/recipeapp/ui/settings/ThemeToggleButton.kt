package com.example.recipeapp.ui.settings

import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ThemeToggleButton(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = viewModel()
) {
    val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()
    IconButton(onClick = viewModel::toggleTheme, modifier = modifier) {
        Text(text = if (isDarkTheme) "☀️" else "🌙")
    }
}
