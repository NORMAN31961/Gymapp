package com.example.gym.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gym.MainActivity
import com.example.gym.databinding.FragmentRegisterBinding
import com.example.gym.model.UsuariosModel
import com.example.gym.models.ViewModelUsuario
import conexiondb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterFragment : Fragment() {
    val usuarioViewmodel : ViewModelUsuario by activityViewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get()= _binding!!
    private lateinit var activity : AppCompatActivity

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
        activity = requireActivity() as AppCompatActivity
        init()
    }

    private fun init() {
        llenarSpinner()
        event_Register()
        event_Back_Button()
    }

    private fun llenarSpinner() {
        val opciones = listOf("Principiante", "Intermedio", "Avanzado")
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            opciones
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerExperience.adapter = adapter
    }

    private fun ObetenerDatosUsuarios():UsuariosModel {
        return UsuariosModel(
            binding.nameEditTextRegister.text.toString(),
            binding.emailEditTextLogin.text.toString(),
            binding.passwordEditTextRegister.text.toString(),
            binding.spinnerExperience.selectedItem.toString(),
            "Free"

        )
    }
    private fun insertarUsuarios(mUsua : UsuariosModel){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val call = getRetrofit().create(conexiondb::class.java).InstarUsuarios(mUsua)
                if (call.isSuccessful && call.body()!= null){
                    withContext(Dispatchers.Main){
                        usuarioViewmodel.addUsuario(mUsua)
                    }
                }else{
                    Log.e("dap","Error no se encontro la informacion")
                }
            }
            catch (e : Exception){
                Log.e("dap","No se pudo conectar a la base de datos",e)
            }

        }
    }
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(conexiondb.url)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun event_Register() {
        binding.confirmButtonRegister.setOnClickListener {
            insertarUsuarios(ObetenerDatosUsuarios())
            (activity as MainActivity).replaceFragment(LoginFragment())
        }
    }

   
}