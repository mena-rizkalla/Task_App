package com.example.taskapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Array.set

class TaskItemAdapter() : ListAdapter<Task,TaskItemAdapter.ViewHolder>(TaskDiffItemCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.taskName.text = task.taskName
        holder.taskDone.isChecked = task.taskDone

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName = itemView.findViewById<TextView>(R.id.task)
        val taskDone = itemView.findViewById<CheckBox>(R.id.task_done)
    }
}