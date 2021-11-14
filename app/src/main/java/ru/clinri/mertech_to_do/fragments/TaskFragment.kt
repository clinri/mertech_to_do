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

import ru.clinri.mertech_to_do.activities.MainApp
import ru.clinri.mertech_to_do.activities.NewTaskActivity
import ru.clinri.mertech_to_do.databinding.FragmentTaskBinding
import ru.clinri.mertech_to_do.db.MainViewModel


class TaskFragment : BaseFragment() {
    private lateinit var binding: FragmentTaskBinding
    private lateinit var editLauncher: ActivityResultLauncher<Intent>

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

    private fun onEditResult(){
        editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                Log.d("MyLog","title: ${it.data?.getStringExtra(TITLE_KEY)}")
                Log.d("MyLog","discription: ${it.data?.getStringExtra(DISC_KEY)}")
            }
        }
    }

    companion object {
        const val TITLE_KEY = "title_key"
        const val DISC_KEY = "disk_key"
        @JvmStatic
        fun newInstance() = TaskFragment()
    }
}