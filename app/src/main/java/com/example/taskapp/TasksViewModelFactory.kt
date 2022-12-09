package com.example.taskapp

import android.widget.ViewSwitcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import kotlin.jvm.Throws

class TasksViewModelFactory(val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)){
            return TasksViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown error")
    }
}