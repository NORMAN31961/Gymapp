package com.example.gym

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gym.databinding.FragmentRoutineBinding


class RoutineFragment : Fragment() {

    private var _binding: FragmentRoutineBinding? = null
    private val binding get() = _binding!!
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        event_Back_Button()
        event_Change_Place()
        event_Init_Routine()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun event_Change_Place() {
        binding.buttonChangePlaceRoutine.setOnClickListener {
            if(binding.textViewPlaceRoutine.text == "CASA"){
                binding.textViewPlaceRoutine.text = "GYM"
            }else{
                binding.textViewPlaceRoutine.text = "CASA"
            }

        }
    }

    private fun event_Init_Routine() {
        binding.buttonInitRutine.setOnClickListener {
            (activity as MainActivity).replaceFragment(ExcerciseFragment())
        }
    }


}