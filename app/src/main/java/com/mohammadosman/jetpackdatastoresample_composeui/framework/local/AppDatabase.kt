package com.mohammadosman.jetpackdatastoresample_composeui.framework.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mohammadosman.jetpackdatastoresample_composeui.framework.local.model.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Database(
    entities = [TaskEntity::class], version = 1
)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null


        operator fun invoke(context: Context) =
            instance ?: synchronized(this) {
                instance ?: initDb(context).also {
                    instance = it
                }
            }

        private fun initDb(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "note_db"
        ).addCallback(Callback()).build()


        class Callback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val dao = instance?.taskDao()
                CoroutineScope(IO).launch {
                    dao?.apply {
                        insertTask(
                            TaskEntity(
                                id = "1",
                                title = "Running",
                                body = "Let's Run Today.",
                                isImportant = true,
                                isCompleted = false
                            )

                        )



                        insertTask(
                            TaskEntity(
                                id = "2",
                                title = "Projects",
                                body = "Humphh! Already have 2 projects pending...",
                                isImportant = true,
                                isCompleted = false
                            )
                        )

                        insertTask(
                            TaskEntity(
                                id = "3",
                                title = "Eating",
                                body = "Eating Completing",
                                isImportant = false,
                                isCompleted = true
                            )

                        )
                        insertTask(
                                TaskEntity(
                                    id = "4",
                                    title = "Gym",
                                    body = "Gym at 6.",
                                    isImportant = true,
                                    isCompleted = false
                                )
                        )


                    }

                }
            }

        }

    }


}