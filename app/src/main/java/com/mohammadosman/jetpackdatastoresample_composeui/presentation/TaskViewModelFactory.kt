package com.mohammadosman.jetpackdatastoresample_composeui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.datastore.PreferenceManager
import com.mohammadosman.jetpackdatastoresample_composeui.usecase.GetFilteredTask

class TaskViewModelFactory(
    private val useCase: GetFilteredTask,
    private val preferenceManager: PreferenceManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(
                useCase = useCase,
                preferenceManager = preferenceManager
            ) as T
        }
        throw IllegalAccessException("unknown viewmodel")
    }
}