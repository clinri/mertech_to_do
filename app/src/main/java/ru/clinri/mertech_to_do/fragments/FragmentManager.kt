package ru.clinri.mertech_to_do.fragments

import androidx.appcompat.app.AppCompatActivity
import ru.clinri.mertech_to_do.R

object FragmentManager {
    // чтобы можно быо использоваь функции без инициализации класса
    var currentFrag: BaseFragment? = null

    fun setFragment(newFrag: BaseFragment, activity: AppCompatActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeHolder, newFrag)
        transaction.commit() // применение всех действий которые указаны выше
        currentFrag = newFrag // получаем список задач, новую задачу или редактируем текщую
    }

}