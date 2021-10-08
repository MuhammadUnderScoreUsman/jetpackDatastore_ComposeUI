package com.mohammadosman.jetpackdatastoresample_composeui.framework.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.FilterOrder.*
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Query("Select * from task order by is_completed DESC")
    fun getCompletedTask(): Flow<List<TaskEntity>>

    @Query("Select * from task order by is_important DESC")
    fun getImportantTask(): Flow<List<TaskEntity>>

    @Query("Select * from task")
    fun getAllTask(): Flow<List<TaskEntity>>


    fun getFilteredTask(filterOrder: FilterOrder): Flow<List<TaskEntity>> {
        return when (filterOrder) {
            COMPLETED -> getCompletedTask()
            IMPORTANT -> getImportantTask()
            UNFILTERED -> getAllTask()
        }
    }

}