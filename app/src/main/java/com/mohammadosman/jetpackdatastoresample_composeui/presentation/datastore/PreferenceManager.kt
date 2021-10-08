package com.mohammadosman.jetpackdatastoresample_composeui.presentation.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.FilterOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val SORT = "sort"
private val Context.dataStore by preferencesDataStore(name = SORT)

class PreferenceManager(
    private val ctx: Context
) {
    companion object {
        const val filters = "filters"
        const val IMPORTANT = "important"
        const val COMPLETE = "complete"
        const val UNFILTERED = "unfiltered"
    }

    private val filterK = stringPreferencesKey(filters)

    val filter: Flow<FilterOrder> = ctx.dataStore.data.catch { e ->
        if (e is IOException) {
            emit(emptyPreferences())
        } else {
            throw e
        }
    }.map { pref ->
        when (pref[filterK] ?: "") {
            IMPORTANT -> FilterOrder.IMPORTANT
            COMPLETE -> FilterOrder.COMPLETED
            UNFILTERED -> FilterOrder.UNFILTERED
            else -> FilterOrder.UNFILTERED
        }
    }


    suspend fun setOrder(filterOrder: FilterOrder) {
        ctx.dataStore.edit { mutablePreferences ->
            mutablePreferences[filterK] = when (filterOrder) {
                FilterOrder.IMPORTANT -> IMPORTANT
                FilterOrder.COMPLETED -> COMPLETE
                FilterOrder.UNFILTERED -> UNFILTERED
            }
        }
    }
}