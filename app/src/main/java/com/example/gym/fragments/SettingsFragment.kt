package com.example.gym.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gym.MainActivity
import com.example.gym.UserSession
import com.example.gym.databinding.FragmentSettingsBinding
import com.example.gym.models.ViewModelUsuario
import conexiondb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    val usuarioViewmodel: ViewModelUsuario by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        event_Back_Button()
        event_Login_Button()
        actualizarEstadoBotonLogin()
        actualizarEstadoBotonDeleteAccount()
        eventdeleteAccount()
    }

    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun event_Login_Button() {
        binding.loginSettings.setOnClickListener {
            (activity as MainActivity).replaceFragment(LoginFragment())
        }

    }

    private fun actualizarEstadoBotonLogin() {
        binding.loginSettings.apply {
            isEnabled = !UserSession.isLoggedIn
            alpha = if (UserSession.isLoggedIn) 0.5f else 1.0f
        }
    }
    private fun actualizarEstadoBotonDeleteAccount() {
        binding.deleteAccount.apply {
            isEnabled = UserSession.isLoggedIn
            alpha = if (!UserSession.isLoggedIn) 0.5f else 1.0f
        }
    }

    private fun eventdeleteAccount() {
        binding.deleteAccount.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun launchHomeCustomerActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showConfirmationDialog() {
        val userId = UserSession.userId
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Eliminar Cuenta")
        builder.setMessage("¿Estás seguro de que deseas eliminar tu cuenta?")
        builder.setPositiveButton("Sí") { _, _ ->
            deleteAccount(userId)
            UserSession.isLoggedIn = false
            UserSession.userName = "Usuario"
            launchHomeCustomerActivity()
        }
        builder.setNegativeButton("No", null)
        builder.create().show()

    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(conexiondb.url)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun deleteAccount(userId: Int){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getRetrofit().create(conexiondb::class.java).EliminarUsuario(userId)
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main){
                        usuarioViewmodel.removeUsuario(userId)
                    }
                } else {
                    Log.e("dap", "Error al consultar usuarios")
                }
            } catch (e: Exception) {
                Log.e("dap", "No se pudo conectar a la base de datos", e)
            }
        }
    }
}