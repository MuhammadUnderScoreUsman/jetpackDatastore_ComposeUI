package com.mohammadosman.jetpackdatastoresample_composeui.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohammadosman.jetpackdatastoresample_composeui.presentation.common.BaseApplication

@ExperimentalAnimationApi
class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskApp(viewModel = viewModel(factory =
                (application as BaseApplication)
                    .viewModelFactory))
        }
    }
}