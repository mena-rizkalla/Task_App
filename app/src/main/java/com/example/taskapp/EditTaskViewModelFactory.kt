package com.example.taskapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditTaskViewModelFactory(private val dao: TaskDao ,private val taskId : Long) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTaskViewModel::class.java))
            return EditTaskViewModel(dao,taskId) as T

        throw IllegalArgumentException("unKnow ViewModel")
    }
}