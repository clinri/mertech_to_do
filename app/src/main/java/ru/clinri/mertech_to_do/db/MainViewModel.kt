package ru.clinri.mertech_to_do.db

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.clinri.mertech_to_do.entities.TaskListItems
import java.lang.IllegalArgumentException

class MainViewModel(dataBase: MainDataBase):ViewModel() {
    val dao = dataBase.getDao()
    val allTasks: LiveData<List<TaskListItems>> = dao.getAllTasks().asLiveData()

    fun insertTask(task: TaskListItems) = viewModelScope.launch {
        dao.insertTask(task)
    }

    class MainViewModelFactory(val dataBase: MainDataBase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(dataBase) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}