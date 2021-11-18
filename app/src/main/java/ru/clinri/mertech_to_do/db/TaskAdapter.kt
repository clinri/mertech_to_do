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

class TaskAdapter(private  val listener: Listener) : ListAdapter<TaskListItems, TaskAdapter.ItemHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position),listener)
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ToDoListItemBinding.bind(view)

        fun setData(task: TaskListItems, listener: Listener) = with(binding){
            tvTitle.text = task.nameTask
            tvDiscription.text = task.infoTask
            tvDeadLineDate.text = task.deadlineDate
            itemView.setOnClickListener{
                listener.onClickItem(task)
            }
            tvComplete.isChecked = if (task.complete==1) true else false
            imDelete.setOnClickListener({
                listener.deleteItem(task.id!!)
            })
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

    interface Listener{
        fun deleteItem(id: Int)
        fun onClickItem(task: TaskListItems)
        //fun checkTaskComplete(id:Int)
    }
}