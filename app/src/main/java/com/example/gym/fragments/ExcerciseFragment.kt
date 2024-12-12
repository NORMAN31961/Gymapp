package com.example.gym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gym.MainActivity
import com.example.gym.databinding.FragmentExcerciseBinding

class ExcerciseFragment : Fragment() {
    private var _binding : FragmentExcerciseBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        event_Back_Button()
        event_Next()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExcerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun event_Next() {
        binding.btnNext.setOnClickListener {
            (activity as MainActivity).replaceFragment(CompleteRoutineFragment())
        }
    }


}