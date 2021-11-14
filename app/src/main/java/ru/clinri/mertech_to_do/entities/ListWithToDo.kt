package ru.clinri.mertech_to_do.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task_list_items")
data class TaskListItems(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name_task") //задача
    val nameTask: String,

    @ColumnInfo(name = "info_task") //описание задачи
    val infoTask: String,

    @ColumnInfo(name = "deadline_date")
    val deadlineDate: String,

    @ColumnInfo(name = "complete")
    val complete: Boolean = false,

    ): Serializable
