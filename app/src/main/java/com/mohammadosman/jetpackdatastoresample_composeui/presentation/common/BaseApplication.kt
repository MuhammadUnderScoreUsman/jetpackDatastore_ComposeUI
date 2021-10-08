package com.mohammadosman.jetpackdatastoresample_composeui.presentation.common

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.AppDatabase
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.TaskDao
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.TaskViewModelFactory
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.datastore.PreferenceManager
import com.mohammadosman.jetpackdatastoresample_composeui.usecase.GetFilteredTask

class BaseApplication : Application() {

    private lateinit var dao: TaskDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var getFilteredTask: GetFilteredTask
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate() {
        super.onCreate()
        preferenceManager = PreferenceManager(this)
        appDatabase = AppDatabase.invoke(this)
        dao = appDatabase.taskDao()
        getFilteredTask = GetFilteredTask(dao = dao)
        viewModelFactory = TaskViewModelFactory(
            useCase = getFilteredTask,
            preferenceManager = preferenceManager
        )
    }

}