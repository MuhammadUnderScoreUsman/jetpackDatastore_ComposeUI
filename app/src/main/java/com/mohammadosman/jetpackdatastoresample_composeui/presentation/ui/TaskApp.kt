package com.mohammadosman.jetpackdatastoresample_composeui.presentation.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.TaskViewModel
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.ui.task.TaskScreen
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.ui.theme.TaskTheme

@ExperimentalAnimationApi
@Composable
fun TaskApp(viewModel: TaskViewModel) {
    TaskTheme(true) {
        TaskScreen(viewmodel = viewModel)
    }
}