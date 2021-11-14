package ru.clinri.mertech_to_do.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.clinri.mertech_to_do.R
import ru.clinri.mertech_to_do.databinding.FragmentTaskBinding


class TaskFragment : BaseFragment() {
    private lateinit var binding: FragmentTaskBinding
    override fun onClickNew() {
        TODO("Not yet implemented")
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