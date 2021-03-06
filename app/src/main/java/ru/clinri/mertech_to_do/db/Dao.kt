package ru.clinri.mertech_to_do.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.clinri.mertech_to_do.entities.TaskListItems

@Dao
interface Dao { // Data access object
    @Query("SELECT * FROM task_list_items") // чтение
    // Flow - подключает БД к списку и автоматически обновляет
    fun getAllTasks(): Flow<List<TaskListItems>>

    @Query("DELETE FROM task_list_items WHERE id IS :id") // чтение
    suspend fun deleteTask(id: Int)

    @Insert // запись
    //suspend - функции запускаются внутри Coroutines
    suspend fun insertTask(task: TaskListItems)

    @Update // обновление/перезапись
    suspend fun updateTask(task: TaskListItems)
}