package com.example.gym.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gym.CoachActivity
import com.example.gym.databinding.FragmentCoachSeeRoutinesBinding

class CoachSeeRoutinesFragment : Fragment() {
    private var _binding: FragmentCoachSeeRoutinesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCoachSeeRoutinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        event_Back_Button()
        event_Assing_Button()

    }

    private fun event_Back_Button() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun event_Assing_Button() {
        (activity as CoachActivity).replaceFragment(Assign_Routines_To_Customers_Fragment())
    }



}