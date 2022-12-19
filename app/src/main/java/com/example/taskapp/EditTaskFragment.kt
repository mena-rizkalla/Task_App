package com.example.taskapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.taskapp.databinding.FragmentEditTaskBinding
import kotlinx.coroutines.launch

class EditTaskFragment() : Fragment() {
    private var _binding : FragmentEditTaskBinding? = null
    private val binding  get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditTaskBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao

        val editTaskViewModelFactory = EditTaskViewModelFactory(dao,taskId)
        val editTaskViewModel  = ViewModelProvider(this,editTaskViewModelFactory).get(EditTaskViewModel::class.java)
        binding.viewModel = editTaskViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        editTaskViewModel.navigateToList.observe(viewLifecycleOwner, Observer {
            it ->
            if (it){
                view.findNavController().navigate(R.id.action_editTaskFragment_to_tasksFragment)
                editTaskViewModel.onNavigateToList()
            }
        })
        binding.UpdateButton.setOnClickListener {
            val text =  binding.taskName.text.toString()
            editTaskViewModel.updateTask(text)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}