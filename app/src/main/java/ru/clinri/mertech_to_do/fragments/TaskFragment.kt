package ru.clinri.mertech_to_do.fragments

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import ru.clinri.mertech_to_do.activities.MainApp
import ru.clinri.mertech_to_do.activities.NewTaskActivity
import ru.clinri.mertech_to_do.databinding.FragmentTaskBinding
import ru.clinri.mertech_to_do.db.MainViewModel


class TaskFragment : BaseFragment() {
    private lateinit var binding: FragmentTaskBinding
    private val mainViewModel: MainViewModel by activityViewModels{
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }
    override fun onClickNew() {
        startActivity(Intent(activity,NewTaskActivity::class.java))
        //mainViewModel.insertTask()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mainViewModel.allTasks.observe(this,{
//            it
//        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = TaskFragment()
    }
}