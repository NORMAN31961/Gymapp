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
import com.example.gym.CoachActivity
import com.example.gym.R
import com.example.gym.adapter.EjercicioAdapter
import com.example.gym.databinding.FragmentAddExcerciseToRoutineBinding
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


class Add_Excercise_to_Routine_Fragment : Fragment() {

    private var _binding: FragmentAddExcerciseToRoutineBinding? = null
    private val binding get() = _binding!!

    private val ejercicioViewModel = EjercicioViewModel()
    lateinit var EjercicioAdapter: EjercicioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddExcerciseToRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        listar_Ejercicio()
        event_Back_Button()
        event_Next_Button()
        initRecyclerView()
        observerViewModel()
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

    private fun event_Back_Button() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(conexiondb.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun event_Next_Button() {
        binding.btnNext.setOnClickListener {
            (activity as CoachActivity).replaceFragment(Assign_Routines_To_Customers_Fragment())
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerViewExercises
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        EjercicioAdapter = EjercicioAdapter(
            ejercicioViewModel.datalistEjercicio.value ?: mutableListOf(),
            onViewClick = { selectedEjercicio, position ->
                launchFragmentExercise(selectedEjercicio, position)
            }
        )
        recyclerView.adapter = EjercicioAdapter
    }

    private fun launchFragmentExercise(
        selectedEjercicio: EjercicioModel? = null,
        position: Int = -1
    ) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add<ExcerciseFragment>(
                R.id.main, args = bundleOf(
                    "BUNDLE_EXCERCISE_NAME" to Gson().toJson(selectedEjercicio),
                    "BUNDLE_EXCERCISE_POSITION" to position
                )
            )
            addToBackStack(null)
        }
    }
}
