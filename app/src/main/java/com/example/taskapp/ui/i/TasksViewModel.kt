package com.example.taskapp.ui.i

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.TaskDao
import com.example.taskapp.model.Task
import kotlinx.coroutines.launch

class TasksViewModel(val taskDao: TaskDao) : ViewModel() {
    var newTaskName =""
    val tasks = taskDao.getAll()
    private val _navigationToTask = MutableLiveData<Long?>()
    val navigationToTask :LiveData<Long?> get() = _navigationToTask
    fun addTask(){
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            taskDao.insert(task)
        }
    }

    fun onTaskClicked(taskId : Long?){
        _navigationToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigationToTask.value = null
    }
}