package com.geektech.hw_1_4.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geektech.hw_1_4.App
import com.geektech.hw_1_4.R
import com.geektech.hw_1_4.databinding.FragmentHomeBinding
import com.geektech.hw_1_4.model.Task
import com.geektech.hw_1_4.ui.home.adapter.TaskAdapter
import com.geektech.hw_1_4.ui.task.TaskFragment.Companion.TASK_KEY
import com.geektech.hw_1_4.ui.task.TaskFragment.Companion.TASK_REQUEST

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this::onLongClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setData()
        binding.recyclerView.adapter = adapter
        binding.tab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }
    private fun setData(){
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }
    private fun onLongClick(task: Task){

        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Do you want to delete?")
        alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })
        alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                App.db.taskDao().delete(task)
                setData()
            }
        })
        alertDialog.create().show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}