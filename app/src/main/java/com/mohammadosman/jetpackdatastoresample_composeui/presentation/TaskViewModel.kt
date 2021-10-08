package com.mohammadosman.jetpackdatastoresample_composeui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadosman.jetpackdatastoresample_composeui.domain.Task
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.FilterOrder
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.datastore.PreferenceManager
import com.mohammadosman.jetpackdatastoresample_composeui.usecase.GetFilteredTask
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(
    private val useCase : GetFilteredTask,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    val filter = preferenceManager.filter

    val tasks: StateFlow<List<Task>>
        get() = filter.flatMapLatest {
            useCase.getTask(it)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    fun updateFilter(filter: FilterOrder) {
        viewModelScope.launch {
            preferenceManager.setOrder(filter)
        }
    }

}