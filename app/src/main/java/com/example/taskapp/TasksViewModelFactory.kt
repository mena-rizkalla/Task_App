package com.example.taskapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.ui.i.TasksViewModel
import java.lang.IllegalArgumentException

class TasksViewModelFactory(val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)){
            return TasksViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown error")
    }
}