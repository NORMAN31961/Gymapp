package com.example.gym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gym.MainActivity
import com.example.gym.R
import com.example.gym.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        event_Login()
        event_Register()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    private fun event_Login() {
        binding.confirmButtonLoggin.setOnClickListener {
            (activity as MainActivity).replaceFragment(ExcerciseFragment())
            println("sss")
        }
    }

    private fun event_Register() {
        binding.registerLinkLogin.setOnClickListener {
            (activity as MainActivity).replaceFragment(RegisterFragment())
        }

    }

}