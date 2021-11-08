package ru.clinri.mertech_to_do.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.clinri.mertech_to_do.entities.TaskListItem

@Database(entities = [TaskListItem::class], version = 1)
abstract class MainDataBase : RoomDatabase() {

    companion object { //все что описано сдесь без инициализации класса
        @Volatile //поле мгновенно становится доступным для всех потоков
        private var INSTANCE: MainDataBase? = null //переменная возвращаемая при обращении к БД

        fun getDataBase(context: Context): MainDataBase {
            // ? - оператор Элвиса (если то что слева null, вернуть то что справа)
            // synchronized - разграничивает доступ нескоьким потокам к одной БД
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder( // предоставляет ссылку на файл с БД
                    context.applicationContext,
                    MainDataBase::class.java,
                    "mertech_tasks.db"
                ).build()
                instance
            }

        }

    }
}