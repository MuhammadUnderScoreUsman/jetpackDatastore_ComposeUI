package com.mohammadosman.jetpackdatastoresample_composeui.usecase

import android.content.Context
import com.mohammadosman.jetpackdatastoresample_composeui.domain.Task
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.AppDatabase
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.FilterOrder
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.TaskDao
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetFilteredTask(
    private val dao: TaskDao
) {

    fun getTask(filterOrder: FilterOrder): Flow<List<Task>> {
        return flow {
            val list = dao.getFilteredTask(filterOrder)
                .first().let {
                    it.map { taskEntity ->
                        taskEntity.toDomain()
                    }
                }
            emit(list)
        }.catch { e ->
            e.localizedMessage
        }
    }
}


fun TaskEntity.toDomain(): Task {
    return Task(
        id, title, body, isImportant, isCompleted
    )
}