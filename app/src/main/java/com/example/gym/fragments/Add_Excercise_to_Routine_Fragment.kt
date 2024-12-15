package com.example.gym.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gym.CoachActivity
import com.example.gym.databinding.FragmentAddExcerciseToRoutineBinding


class Add_Excercise_to_Routine_Fragment : Fragment() {

    private var _binding: FragmentAddExcerciseToRoutineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddExcerciseToRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BackStack", "BackStack count: ${parentFragmentManager.backStackEntryCount}")
        for (i in 0 until parentFragmentManager.backStackEntryCount) {
            Log.d("BackStack", "Fragment: ${parentFragmentManager.getBackStackEntryAt(i).name}")
        }
        init()

    }

    private fun init() {
        event_Back_Button()
        event_Next_Button()
    }

    private fun event_Back_Button() {
        binding.backButton.setOnClickListener {
            Log.d("BackStack", "BackStack count: ${parentFragmentManager.backStackEntryCount}")
            for (i in 0 until parentFragmentManager.backStackEntryCount) {
                Log.d("BackStack", "Fragment: ${parentFragmentManager.getBackStackEntryAt(i).name}")
            }
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun event_Next_Button() {
        binding.btnNext.setOnClickListener {
            (activity as CoachActivity).replaceFragment(Assign_Routines_To_Customers_Fragment())
        }
    }


}