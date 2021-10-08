package com.mohammadosman.jetpackdatastoresample_composeui.domain

data class Task(
    val id: String,
    val title: String,
    val body: String,
    val isImportant: Boolean,
    val isCompleted: Boolean
) {
    companion object {
        fun factory() = Task(
            id = "",
            title = "",
            body = "",
            isImportant = false,
            isCompleted = false
        )
    }
}