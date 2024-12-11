package com.example.gym

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gym.databinding.FragmentCompleteRoutineBinding


class CompleteRoutineFragment : Fragment() {

    private var _binding: FragmentCompleteRoutineBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        event_Home_Button()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCompleteRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun event_Home_Button() {
        binding.btnHome.setOnClickListener {
            (activity as MainActivity).replaceFragment(HomeCustomerFragment())
        }
    }


}