package com.example.recipeapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(context: Context) {

    private val dataStore = context.applicationContext.dataStore

    val isDarkTheme: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DARK_THEME] ?: false
    }

    val sortOrder: Flow<SortOrder> = dataStore.data.map { preferences ->
        preferences[SORT_ORDER]
            ?.let { name -> SortOrder.entries.firstOrNull { it.name == name } }
            ?: SortOrder.BY_DATE
    }

    suspend fun setDarkTheme(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME] = enabled
        }
    }

    suspend fun setSortOrder(order: SortOrder) {
        dataStore.edit { preferences ->
            preferences[SORT_ORDER] = order.name
        }
    }

    private companion object {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val SORT_ORDER = stringPreferencesKey("sort_order")
    }
}
