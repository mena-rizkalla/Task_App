package com.example.taskapp

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskapp.model.Task

@Dao
interface TaskDao {


    @Insert
    suspend fun insert(task : Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE taskId = :taskId")
     fun get(taskId :Long) : LiveData<Task>

    @Query("SELECT * FROM task_table")
    fun getAll() : LiveData<List<Task>>
}