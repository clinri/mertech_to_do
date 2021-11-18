package ru.clinri.mertech_to_do.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.Menu
import android.view.MenuItem
import ru.clinri.mertech_to_do.R
import ru.clinri.mertech_to_do.databinding.ActivityNewTaskBinding
import ru.clinri.mertech_to_do.entities.TaskListItems
import ru.clinri.mertech_to_do.fragments.TaskFragment
import java.text.SimpleDateFormat
import java.util.*

class NewTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewTaskBinding
    private var task: TaskListItems? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBarSettings()
        getTask()
    }

    private fun getTask() {
        val sTask = intent.getSerializableExtra(TaskFragment.NEW_TASK_KEY)
        // sTask - serializable task
        if (sTask != null) {
            task = sTask as TaskListItems
            fillTask()
        }
    }

    private fun fillTask() = with(binding) {
        edTitle.setText(task?.nameTask)
        edDiscription.setText(task?.infoTask)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_task_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.id_save) {
            setMainResult()
        } else if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMainResult() {
        var editState = "new"
        val tempTask: TaskListItems? = if (task == null) {
            createNewTask()
        } else {
            editState = "update"
            updateTask()
        }
        val i = Intent().apply {
            putExtra(TaskFragment.NEW_TASK_KEY, tempTask)
            putExtra(TaskFragment.EDIT_STATE_KEY, editState)
        }
        setResult(RESULT_OK, i)
        finish()
    }

    private fun updateTask(): TaskListItems? = with(binding) {
        return task?.copy(
            nameTask = edTitle.text.toString(),
            infoTask = edDiscription.text.toString()
        )
    }

    private fun createNewTask(): TaskListItems {
        return TaskListItems(
            null,
            binding.edTitle.text.toString(),
            binding.edDiscription.text.toString(),
            getCurrentTime(),
            0
        )
    }

    private fun getCurrentTime(): String {
        val formatter = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())
        return formatter.format(Calendar.getInstance().time)
    }

    private fun actionBarSettings() {
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

    }


}