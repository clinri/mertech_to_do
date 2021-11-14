package ru.clinri.mertech_to_do.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

import ru.clinri.mertech_to_do.activities.MainApp
import ru.clinri.mertech_to_do.activities.NewTaskActivity
import ru.clinri.mertech_to_do.databinding.FragmentTaskBinding
import ru.clinri.mertech_to_do.db.MainViewModel
import ru.clinri.mertech_to_do.db.TaskAdapter
import ru.clinri.mertech_to_do.entities.TaskListItems


class TaskFragment : BaseFragment() {
    private lateinit var binding: FragmentTaskBinding
    private lateinit var editLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: TaskAdapter

    private val mainViewModel: MainViewModel by activityViewModels{
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }
    override fun onClickNew() {
        editLauncher.launch(Intent(activity,NewTaskActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onEditResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView()=with(binding){
        rcViewTask.layoutManager = LinearLayoutManager(activity)
        adapter = TaskAdapter()
        rcViewTask.adapter = adapter
    }

    private fun observer(){
        mainViewModel.allTasks.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
    }

    private fun onEditResult(){
        editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                //Log.d("MyLog","title: ${it.data?.getStringExtra(TITLE_KEY)}")
                mainViewModel.insertTask(it.data?.getSerializableExtra(NEW_TASK_KEY) as TaskListItems)

            }
        }
    }

    companion object {
        const val NEW_TASK_KEY = "new_task_key"
        @JvmStatic
        fun newInstance() = TaskFragment()
    }
}