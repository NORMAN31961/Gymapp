package com.example.gym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gym.MainActivity
import com.example.gym.databinding.FragmentRegisterBinding
import com.example.gym.model.UsuariosModel
import com.example.gym.models.ViewModelUsuario

class RegisterFragment : Fragment() {
    val usuarioViewmodel : ViewModelUsuario by activityViewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        event_Register()
    }

    private fun event_Register() {
        binding.confirmButtonRegister.setOnClickListener {
            (activity as MainActivity).replaceFragment(LoginFragment())
        }
    }

   
}