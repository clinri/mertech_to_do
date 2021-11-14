package ru.clinri.mertech_to_do.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.clinri.mertech_to_do.R
import ru.clinri.mertech_to_do.databinding.ActivityMainBinding
import ru.clinri.mertech_to_do.fragments.FragmentManager
import ru.clinri.mertech_to_do.fragments.TaskFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentManager.setFragment(TaskFragment.newInstance(),this)
        setBottomNavListener()
    }

    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.addTask -> {
                    Log.d("MyLog", "Add task")
                    FragmentManager.currentFrag?.onClickNew()
                }
            }
            true
        }
    }
}