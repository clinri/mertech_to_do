package ru.clinri.mertech_to_do.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.clinri.mertech_to_do.R
import ru.clinri.mertech_to_do.databinding.ToDoListItemBinding
import ru.clinri.mertech_to_do.entities.TaskListItems

class TaskAdapter : ListAdapter<TaskListItems, TaskAdapter.ItemHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ToDoListItemBinding.bind(view)
        fun setData(task: TaskListItems) = with(binding){
            tvTitle.text = task.nameTask
            tvDiscription.text = task.infoTask
            tvDeadLineDate.text = task.deadlineDate
        }
        companion object{
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(LayoutInflater.from(parent.context).
                inflate(R.layout.to_do_list_item,parent,false))
            }
        }
    }
    class ItemComparator : DiffUtil.ItemCallback<TaskListItems>(){
        override fun areItemsTheSame(oldItem: TaskListItems, newItem: TaskListItems): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskListItems, newItem: TaskListItems): Boolean {
            return oldItem == newItem
        }
    }
}