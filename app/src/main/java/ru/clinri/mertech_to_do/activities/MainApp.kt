package ru.clinri.mertech_to_do.activities

import android.app.Application
import ru.clinri.mertech_to_do.db.MainDataBase

// к переменным этого Активити всегда будет доступ, т.к. он прописан в манифесте
class MainApp :Application() {
    // постоянный доступ к базе данных
    val database by lazy { MainDataBase.getDataBase(this) }
}