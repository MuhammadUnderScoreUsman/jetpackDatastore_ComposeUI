package com.mohammadosman.jetpackdatastoresample_composeui.framework.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "is_important")
    val isImportant: Boolean,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean
)