package com.example.gym.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym.MainActivity
import com.example.gym.adapter.EjercicioAdapter
import com.example.gym.databinding.FragmentRoutineBinding
import com.example.gym.model.EjercicioViewModel
import conexiondb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoutineFragment : Fragment() {

    private var _binding: FragmentRoutineBinding? = null
    private val binding get() = _binding!!

    private val ejercicioViewModel = EjercicioViewModel()
    lateinit var EjercicioAdapter: EjercicioAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        initRecyclerView()
        event_Back_Button()
        event_Change_Place()
        listar_Ejercicio()
        event_Init_Routine()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(conexiondb.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun listar_Ejercicio(){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val call = getRetrofit().create(conexiondb::class.java).ConsultarEjercicio()
                if(call.isSuccessful && call.body() != null){
                    withContext(Dispatchers.Main){
                      ejercicioViewModel.addEjercicioList(call.body()!!.toMutableList())
                    }
                }else{
                    Log.e("dap", "Error no se encontro informacion")
                }
            }catch (e: Exception){
                Log.e("dap", "Error No se pudo Conectar a la base de datos", e)
            }
        }
    }

    private fun initRecyclerView(){
        val recyclerView = binding.recyclerViewRoutineExercise
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        EjercicioAdapter = EjercicioAdapter(ejercicioViewModel.datalistEjercicio.value?: mutableListOf())
        recyclerView.adapter = EjercicioAdapter
    }


}