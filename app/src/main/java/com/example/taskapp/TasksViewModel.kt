package com.example.taskapp

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val taskDao: TaskDao) : ViewModel() {
    var newTaskName =""
    var tasks = taskDao.getAll()
    val tasksString = Transformations.map(tasks){
        tasks -> formateTasks(tasks)
    }

    fun formateTasks(tasks: List<Task>) : String{
        return tasks.fold(""){
            str, item -> str+ '\n' + formatTask(item)
        }
    }
    fun formatTask(task: Task) : String{
        var str = "ID:${task.taskId}"
        str += '\n' + "Name: ${task.taskName}"
        str += '\n' + "Complete: ${task.taskDone} + '\n"
        return str
    }

    fun addTask(){
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            taskDao.insert(task)
        }

    }

}