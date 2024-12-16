package com.example.gym.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym.MainActivity
import com.example.gym.R
import com.example.gym.adapter.EjercicioAdapter
import com.example.gym.databinding.FragmentRoutineBinding
import com.example.gym.model.EjercicioModel
import com.example.gym.model.EjercicioViewModel
import com.google.gson.Gson
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
        listar_Ejercicio()
        event_Back_Button()
        event_Change_Place()
        event_Init_Routine()
        initRecyclerView()
        observerViewModel()
    }
    private fun event_Back_Button() {
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun event_Change_Place() {
        binding.buttonChangePlaceRoutine.setOnClickListener {
            if (binding.textViewPlaceRoutine.text == "CASA") {
                binding.textViewPlaceRoutine.text = "GYM"
            } else {
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

    private fun listar_Ejercicio() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(conexiondb::class.java).ConsultarEjercicio()
                if (call.isSuccessful && call.body() != null) {
                    withContext(Dispatchers.Main) {
                        ejercicioViewModel.addEjercicioList(call.body()!!.toMutableList())
                    }
                } else {
                    Log.e("dap", "Error no se encontro informacion")
                }
            } catch (e: Exception) {
                Log.e("dap", "Error No se pudo Conectar a la base de datos", e)
            }
        }
    }

    private fun observerViewModel() {
        ejercicioViewModel.datalistEjercicio.observe(viewLifecycleOwner) { ejercicios ->
            EjercicioAdapter.ejerciciosList = ejercicios
            EjercicioAdapter.notifyDataSetChanged()

        }
    }


    private fun launchFragmentExercise(selectedEjercicio: EjercicioModel? = null, position: Int = -1) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add<ExcerciseFragment>(
                R.id.main, args = bundleOf(
                    "BUNDLE_EXCERCISE_NAME" to Gson().toJson(selectedEjercicio),
                    "BUNDLE_EXCERCISE_POSITION" to position
                ))
            addToBackStack(null)
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerViewRoutineExercise
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        EjercicioAdapter = EjercicioAdapter(
            ejercicioViewModel.datalistEjercicio.value ?: mutableListOf(),
            onViewClick = { selectedEjercicio, position ->
                launchFragmentExercise(selectedEjercicio, position)
            }
        )
        recyclerView.adapter = EjercicioAdapter
    }
}