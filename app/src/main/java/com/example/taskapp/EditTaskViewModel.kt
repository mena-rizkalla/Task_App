package com.example.taskapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class EditTaskViewModel(private val dao: TaskDao ,private val taskId : Long) : ViewModel() {

     var task = dao.get(taskId)

    private val _navigateToList = MutableLiveData<Boolean>()
     val navigateToList : LiveData<Boolean>  get() = _navigateToList

    fun updateTask(){
        viewModelScope.launch {
            dao.update(task.value!!)
            _navigateToList.value = true
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            dao.delete(task.value!!)
            _navigateToList.value = true
        }
    }

    fun onNavigateToList(){
        _navigateToList.value = false
    }

}