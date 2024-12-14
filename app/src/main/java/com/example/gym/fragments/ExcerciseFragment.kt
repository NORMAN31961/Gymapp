package com.example.gym.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gym.MainActivity
import com.example.gym.databinding.FragmentExcerciseBinding
import com.example.gym.model.EjercicioModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ExcerciseFragment : Fragment() {
    private var _binding : FragmentExcerciseBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        event_Back_Button()
        argumnetosRecibidos()
        event_Next()
    }

    private fun argumnetosRecibidos() {
        arguments?.let{
            val jsonString = it.getString("BUNDLE_EXCERCISE_NAME")
            if(jsonString != null){
                try {
                    val ejercicio = Gson().fromJson(jsonString, EjercicioModel::class.java)
                    asignarVariablesVisualizar(ejercicio)
                }catch (e: Exception){
                    Log.e("argumentosRecibidos", "Error: ${e.message}")
                }
            }

        }
    }

    private fun asignarVariablesVisualizar(ejercicio: EjercicioModel) {
        binding.textViewExcerciseTitle.setText(ejercicio.Nombre)
        binding.tvInstructions.setText(ejercicio.Descripcion)
        Picasso.get()
            .load(ejercicio.Url_Gif)
            .into(binding.gifImage)
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