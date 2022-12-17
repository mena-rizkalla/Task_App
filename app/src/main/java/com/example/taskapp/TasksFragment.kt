package com.example.taskapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.FragmentTasksBinding


class TasksFragment : Fragment() {
    var _binding: FragmentTasksBinding? = null
    val binding get() =  _binding!!
    lateinit var tasksViewModel : TasksViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTasksBinding.inflate(inflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val tasksViewModelFactory = TasksViewModelFactory(dao)
        tasksViewModel = ViewModelProvider(this,tasksViewModelFactory).get(TasksViewModel::class.java)

        binding.taskViewModel = tasksViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter  = TaskItemAdapter{ itemID -> Toast.makeText(context,itemID.toString(),Toast.LENGTH_SHORT).show()}
        binding.taskLists.adapter = adapter

        tasksViewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)

            }
        })



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}