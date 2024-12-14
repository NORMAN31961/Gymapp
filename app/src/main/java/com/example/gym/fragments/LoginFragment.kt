package com.example.gym.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gym.CoachActivity
import com.example.gym.MainActivity
import com.example.gym.UserSession
import com.example.gym.databinding.FragmentLoginBinding
import conexiondb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
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

    private fun launchCoachActivity() {
        val intent = Intent(requireContext(), CoachActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
    private fun launchHomeCustomerActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun event_Login() {
        binding.confirmButtonLogin.setOnClickListener {
            val email = binding.emailEditTextLogin.text.toString()
            val password = binding.passwordEditTextLogin.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor ingresa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            verificarCredenciales(email, password)
        }
    }

    private fun verificarCredenciales(email: String, password: String) {
        if(email == "Coach" && password == "Coach"){
            launchCoachActivity()
            UserSession.userName = "Coach"
            UserSession.isLoggedIn = true

        }else{
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = getRetrofit().create(conexiondb::class.java).Consultausuarios()

                    if (response.isSuccessful && response.body() != null) {
                        val usuarios = response.body()
                        val usuarioEncontrado = usuarios?.firstOrNull {
                            it.Corre == email && it.Contrasena == password
                        }

                        if (usuarioEncontrado != null) {
                            withContext(Dispatchers.Main) {
                                UserSession.userName = usuarioEncontrado.Nombre
                                println(UserSession.userName)
                                UserSession.isLoggedIn = true
                                launchHomeCustomerActivity()
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Error al consultar usuarios", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "No se pudo conectar a la base de datos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(conexiondb.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun event_Register() {
        binding.registerButtonLogin.setOnClickListener {
            (activity as MainActivity).replaceFragment(RegisterFragment())
        }
    }
}
