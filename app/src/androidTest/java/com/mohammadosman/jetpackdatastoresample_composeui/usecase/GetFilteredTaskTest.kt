package com.mohammadosman.jetpackdatastoresample_composeui.usecase

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mohammadosman.jetpackdatastoresample_composeui.domain.Task
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.AppDatabase
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.FilterOrder
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.TaskDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class GetFilteredTaskTest {

    // under test
    lateinit var getFilteredTask: GetFilteredTask

    // dependencies
    lateinit var dao: TaskDao
    lateinit var db: AppDatabase


    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.noteDao()

        getFilteredTask = GetFilteredTask(ApplicationProvider.getApplicationContext())
    }

    @After
    fun teardown() {
        db.close()
    }


    @Test
    fun getList_inCompletedOrder_fromExistingItem() =
        runBlocking {
            val getList = getFilteredTask.getTask(
                FilterOrder.COMPLETED
            )

            val lst = mutableListOf<Task>()

            getList.collect {
                lst.addAll(it)
            }

            Log.d("TAG", "lst: $lst ")
            assert(lst.size > 0)
        }

    @Test
    fun getList_inImportantOrder_fromExistingItem() =
        runBlocking {

            val getList = getFilteredTask.getTask(
                FilterOrder.IMPORTANT
            )

            val lst = mutableListOf<Task>()

            getList.collect {
                lst.addAll(it)
            }

            assert(lst.size > 0)
        }
}