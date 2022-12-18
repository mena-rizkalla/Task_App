package com.example.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.TaskDiffItemCallback
import com.example.taskapp.databinding.TaskItemBinding
import com.example.taskapp.model.Task

class TaskItemAdapter(val clickListener: (taskID:Long) -> Unit)  : ListAdapter<Task, TaskItemAdapter.ViewHolder>(
    TaskDiffItemCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task,clickListener)

    }


    class ViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, clickListener: (taskID: Long) -> Unit){
            binding.taskData =task
            binding.root.setOnClickListener { clickListener(task.taskId) }
        }

    }
}