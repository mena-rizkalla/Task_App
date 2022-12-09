package com.example.taskapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val taskDao: TaskDao) : ViewModel() {
    var newTaskName =""
    var tasks = taskDao.getAll()

    fun addTask(){
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            taskDao.insert(task)
        }

    }

}