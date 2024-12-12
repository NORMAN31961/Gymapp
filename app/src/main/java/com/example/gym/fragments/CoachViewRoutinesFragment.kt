package com.example.gym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gym.R
import com.example.gym.databinding.FragmentCoachViewRoutinesBinding


class CoachViewRoutinesFragment : Fragment() {
    private var _binding: FragmentCoachViewRoutinesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoachViewRoutinesBinding.inflate(inflater, container, false)
        return binding.root
    }


}