package com.example.recipeapp.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.data.SortOrder

@Composable
fun SortMenu(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = viewModel()
) {
    val sortOrder by viewModel.sortOrder.collectAsStateWithLifecycle()
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = "Sort"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SortOrder.entries.forEach { order ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = when (order) {
                                SortOrder.BY_NAME -> "By name"
                                SortOrder.BY_DATE -> "By date"
                            }
                        )
                    },
                    trailingIcon = {
                        if (order == sortOrder) {
                            Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                        }
                    },
                    onClick = {
                        viewModel.setSortOrder(order)
                        expanded = false
                    }
                )
            }
        }
    }
}
