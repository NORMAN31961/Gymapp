package com.example.gym.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gym.CoachActivity
import com.example.gym.HomeCustomerFragment
import com.example.gym.MainActivity
import com.example.gym.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        event_Login()
        event_Register()
        event_Back_Button()
    }

    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun launchCoachActivity() {
        val intent = Intent(requireContext(), CoachActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        
    }
    private fun event_Login() {
        binding.confirmButtonLogin.setOnClickListener {
            launchCoachActivity()
            //(activity as MainActivity).replaceFragment(HomeCoachFragment())
        }
    }

    private fun event_Register() {
        binding.registerButtonLogin.setOnClickListener {
            (activity as MainActivity).replaceFragment(RegisterFragment())
        }
    }

}