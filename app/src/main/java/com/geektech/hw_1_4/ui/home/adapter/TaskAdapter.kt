package com.geektech.hw_1_4.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.hw_1_4.databinding.ItemTaskBinding
import com.geektech.hw_1_4.model.Task
import com.geektech.hw_1_4.task.task

class TaskAdapter(private val onLongClick: (Task) -> Unit) : Adapter<TaskAdapter.TaskViewHolder>() {


    private val data: ArrayList<Task> = arrayListOf()


    fun addTask(task: Task) {
        data.add(0, task)
        notifyItemChanged(0)
    }

    fun addTasks(task: List<Task>) {
        data.clear()
        data.addAll(task)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])

    }


    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }

        }

    }

}





